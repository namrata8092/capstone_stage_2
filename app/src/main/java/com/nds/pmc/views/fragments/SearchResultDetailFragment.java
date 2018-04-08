package com.nds.pmc.views.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nds.pmc.PMCApplication;
import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.common.NetworkRequestManager;
import com.nds.pmc.common.NetworkRequester;
import com.nds.pmc.converter.SearchDetailResponseConverter;
import com.nds.pmc.model.Place;
import com.nds.pmc.model.PlaceDetails;
import com.nds.pmc.services.FavoritePlaceUpdateListener;
import com.nds.pmc.services.UpdateFavoritePlaceToDB;
import com.nds.pmc.tos.requests.PlaceDetailRequest;
import com.nds.pmc.tos.requests.PosterPhotoRequest;
import com.nds.pmc.util.DeviceUtil;
import com.nds.pmc.util.LogUtil;
import com.nds.pmc.util.NetworkUtil;
import com.nds.pmc.views.adapters.ReviewListAdapter;

import java.lang.ref.WeakReference;

/**
 * Created by Namrata on 1/11/2018.
 */

public class SearchResultDetailFragment extends Fragment implements OnMapReadyCallback, FavoritePlaceUpdateListener {
    private Place mPlace;
    private MapView mMapView;
    private ImageView mAddToFavorite, mPosterImage, mImageLink, mPhoneNumberImage, mWebsiteImage;
    private SharedPreferences mSharedPreferences;
    private PMCApplication mPMCApplication;
    private NetworkRequestManager mNetworkRequestManager;
    private LinearLayout mProgressBarContainer, mDetailContainer;
    private PlaceDetails mPlaceDetails;
    private String contactNumber;
    private static boolean reviewsOpened = false;
    private static final String TAG = SearchResultDetailFragment.class.getSimpleName();
    private TextView mOpenSymbolTextView, mWeeklyTimingTextView, mPlaceTitleTextView, mPlaceAddressTextView, mReviewListTextView;
    private RatingBar mResultRatingBar;
    private RecyclerView mReviewListRecyclerView;

    public static SearchResultDetailFragment newInstance(Place place) {
        SearchResultDetailFragment fragment = new SearchResultDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PLACE_BUNDLE_KEY, place);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            if (savedInstanceState.containsKey(Constants.PLACE_BUNDLE_KEY)) {
                mPlace = savedInstanceState.getParcelable(Constants.PLACE_BUNDLE_KEY);
            }
            if (savedInstanceState.containsKey(Constants.PLACE_DETAIL_BUNDLE_KEY)) {
                mPlaceDetails = savedInstanceState.getParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY);
            }
        }else if (getArguments() != null) {
            mPlace = getArguments().getParcelable(Constants.PLACE_BUNDLE_KEY);
        }

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        mPMCApplication = (PMCApplication) getActivity().getApplication();
        mNetworkRequestManager = mPMCApplication.getNetworkRequestManager();
    }

    private void displayErrorFragment(String errorMsg) {
        ErrorFragment errorFragment = ErrorFragment.newInstance(errorMsg);
        getActivity().findViewById(R.id.container).setVisibility(View.VISIBLE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, errorFragment).commit();
    }

    private void hideProgressBar() {
        mProgressBarContainer.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mProgressBarContainer.setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_detail, container, false);

        initializeUIComponents(rootView);

        mProgressBarContainer = (LinearLayout) rootView.findViewById(R.id.progressContainer);
        mDetailContainer = (LinearLayout) rootView.findViewById(R.id.detailContainer);
        if (!NetworkUtil.isDataNetworkAvailable(getContext())) {
            displayErrorFragment(getResources().getString(R.string.network_error));
        } else if (mPlace != null) {
            setPlaceMap(savedInstanceState);
            PlaceDetailRequest placeDetailRequest = new PlaceDetailRequest(mPlace.getId(), getResources().getString(R.string.PLACES_API_KEY));
            String req = placeDetailRequest.createRequest();
            LogUtil.d(TAG, "detail request " + req);
            showProgressBar();
            mNetworkRequestManager.createStringRequest(new WeakReference<NetworkRequester>(searchNetworkRequster), placeDetailRequest.createRequest(),
                    Constants.SEARCH_REQUEST_TAG);
        }
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.REQ_CALL_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    placeCall(contactNumber);
                } else {
                    displayErrorFragment(getString(R.string.error_call_permission_denied));
                }
                break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.PLACE_BUNDLE_KEY, mPlace);
        outState.putParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY, mPlaceDetails);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        if(mMapView!=null)
            mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMapView!=null)
            mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(mMapView!=null)
            mMapView.onLowMemory();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(mPlace!=null && getActivity()!=null){
            googleMap.addMarker(new MarkerOptions()
                    .anchor(0.0f, 1.0f)
                    .position(new LatLng(mPlace.getLatitude(), mPlace.getLongitude())));
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
            googleMap.setMyLocationEnabled(true);
            MapsInitializer.initialize(this.getActivity());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(mPlace.getLatitude(), mPlace.getLongitude()), 10);
            googleMap.animateCamera(cameraUpdate);
        }
    }

    @Override
    public void onSuccess(final int status) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (status == Constants.ADDED_TO_FAVORITE) {
                    setFavoriteIcon(getResources().getDrawable(R.drawable.favorite_on));
                    mSharedPreferences.edit().putBoolean(mPlace.getId(), true).apply();
                } else {
                    setFavoriteIcon(getResources().getDrawable(R.drawable.favorite_off));
                    mSharedPreferences.edit().putBoolean(mPlace.getId(), false).apply();
                }
            }
        });
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), getResources().getString(R.string.adding_favorite_error), Toast.LENGTH_SHORT).show();
    }

    private void initializeUIComponents(View rootView) {
        mMapView = (MapView) rootView.findViewById(R.id.placeLocation);
        mOpenSymbolTextView = (TextView) rootView.findViewById(R.id.openSymbol);
        mWeeklyTimingTextView = (TextView) rootView.findViewById(R.id.weeklyTiming);
        mPosterImage = (ImageView) rootView.findViewById(R.id.posterImage);
        mImageLink = (ImageView) rootView.findViewById(R.id.imageLink);
        mPhoneNumberImage = (ImageView) rootView.findViewById(R.id.phoneNumber);
        mPlaceTitleTextView = (TextView) rootView.findViewById(R.id.placeTitle);
        mPlaceAddressTextView = (TextView) rootView.findViewById(R.id.placeAddress);
        mResultRatingBar = (RatingBar) rootView.findViewById(R.id.resultRating);
        mReviewListTextView = (TextView) rootView.findViewById(R.id.reviews);
        mReviewListRecyclerView = (RecyclerView) rootView.findViewById(R.id.reviewList);
        mWebsiteImage = (ImageView) rootView.findViewById(R.id.website);
        mAddToFavorite = (ImageView) rootView.findViewById(R.id.addToFavorite);
    }

    private void setFavoritePlace() {
        if (mPlace.isWidgetEntry()) {
            mAddToFavorite.setVisibility(View.GONE);
            return;
        }

        if (mSharedPreferences.getBoolean(mPlace.getId(), false)) {
            setFavoriteIcon(getResources().getDrawable(R.drawable.favorite_on));
            mAddToFavorite.setContentDescription(mPlace.getName() + R.string.reader_text_favorite_on);
        } else {
            setFavoriteIcon(getResources().getDrawable(R.drawable.favorite_off));
            mAddToFavorite.setContentDescription(mPlace.getName() + R.string.reader_text_favorite_off);
        }

        mAddToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateFavoritePlaceToDB updateFavoritePlaceToDB = new UpdateFavoritePlaceToDB(getActivity(),
                        mPlace, SearchResultDetailFragment.this);
                updateFavoritePlaceToDB.execute();
            }
        });
    }

    private void setFavoriteIcon(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mAddToFavorite.setBackground(drawable);
        } else
            mAddToFavorite.setImageDrawable(drawable);
    }

    @SuppressLint("MissingPermission")
    private void setPlaceMap(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }

    private void setPlaceOperationHours() {
        if (mPlaceDetails.isOpen()) {
            mOpenSymbolTextView.setText(mPlace.isOpenNow() ? getString(R.string.open_now) : getString(R.string.close_now));
        } else
            mOpenSymbolTextView.setVisibility(View.GONE);
        if (mPlaceDetails.getWeeklyTiming() != null) {
            mWeeklyTimingTextView.setText(mPlaceDetails.getWeeklyTiming());
            mWeeklyTimingTextView.setVisibility(View.VISIBLE);
        }
    }

    private void setPlaceTitleAddress() {
        mPlaceTitleTextView.setText(mPlaceDetails.getPlaceName());
        mPlaceAddressTextView.setText(mPlaceDetails.getPlaceAddress());
        mResultRatingBar.setRating((float) mPlaceDetails.getRating());

    }

    private void setPlacePhoneNumber() {

        if (mPlaceDetails.getPhoneNumber() != null) {
            contactNumber = mPlaceDetails.getPhoneNumber();
            mPhoneNumberImage.setVisibility(View.VISIBLE);
            mPhoneNumberImage.setContentDescription(R.string.reader_text_contact_number + contactNumber);
            mPhoneNumberImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DeviceUtil.checkCallPermissionAvailable(getActivity())) {
                        placeCall(contactNumber);
                        return;
                    } else
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                Constants.REQ_CALL_PERMISSION);
                }
            });
        }
    }

    private void placeCall(String contactNumber) {
        Intent dialNumberIntent = new Intent(Intent.ACTION_CALL);
        dialNumberIntent.setData(Uri.parse(Constants.TELEPHONE_SCHEMA + contactNumber));
        dialNumberIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (dialNumberIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivity(dialNumberIntent);
        }else{
            Toast.makeText(getContext(),getString(R.string.no_application_error), Toast.LENGTH_LONG).show();
        }
    }

    private void setPlaceWebsite() {
        if(mPlaceDetails.getWebSiteUrl()!=null){
            final String url = mPlaceDetails.getWebSiteUrl();
            mWebsiteImage.setVisibility(View.VISIBLE);
            mWebsiteImage.setContentDescription(R.string.reader_text_website_address+url);
            mWebsiteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openUrlInBrowser(url);
                }
            });
        }
    }

    private void openUrlInBrowser(String url) {
        Intent webSiteIntent = new Intent(Intent.ACTION_VIEW);
        webSiteIntent.setData(Uri.parse(url));
        webSiteIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (webSiteIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivity(webSiteIntent);
        }else{
            Toast.makeText(getContext(),getResources().getString(R.string.no_application_error), Toast.LENGTH_LONG).show();
        }
    }

    private NetworkRequester searchNetworkRequster = new NetworkRequester() {
        @Override
        public void onFailure(Throwable error) {
            hideProgressBar();
            displayErrorFragment(getResources().getString(R.string.network_error));
        }

        @Override
        public void onSuccess(String response) {
            hideProgressBar();
            if (response != null && !TextUtils.isEmpty(response)) {
                mPlaceDetails = SearchDetailResponseConverter.getSearchDetailResultModel(response);
                mDetailContainer.setVisibility(View.VISIBLE);
                if(mPlaceDetails!=null && isAdded() && getActivity() != null) {
                    setPostImage();

                    setPlaceTitleAddress();

                    setPlaceOperationHours();

                    setPlacePhoneNumber();

                    setPlaceWebsite();

                    setFavoritePlace();

                    setPlaceReviews();
                }
            }
        }
    };

    private void setPlaceReviews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mReviewListRecyclerView.setLayoutManager(linearLayoutManager);

        if(mPlaceDetails.getReviewDetailList()!=null && !mPlaceDetails.getReviewDetailList().isEmpty()){
            mReviewListTextView.setVisibility(View.VISIBLE);
            mReviewListTextView.setText(getString(R.string.reviews));
            ReviewListAdapter reviewListAdapter = new ReviewListAdapter(getContext(), mPlaceDetails.getReviewDetailList());
            mReviewListRecyclerView.setAdapter(reviewListAdapter);
            mReviewListTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(reviewsOpened){
                        mReviewListTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up, 0);
                        reviewsOpened = false;
                        mReviewListRecyclerView.setVisibility(View.GONE);
                    }else{
                        reviewsOpened = true;
                        mReviewListTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
                        mReviewListRecyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void setPostImage() {
        if(mPlace.getPhotos()!=null && mPlace.getPhotos().get(0).getMapLink()!=null){
            String mapLink = extractUrlFromLink(mPlace.getPhotos().get(0).getMapLink());
            mImageLink.setVisibility(View.VISIBLE);
            mImageLink.setContentDescription(R.string.reader_text_image_link+mPlace.getName());
            mImageLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openUrlInBrowser(mapLink);
                }
            });
        }
        String url = null;
        if (mPlaceDetails.getPhotoList() != null && !mPlaceDetails.getPhotoList().isEmpty()) {
            PosterPhotoRequest posterPhotoRequest = new PosterPhotoRequest(mPlaceDetails.getPhotoList().get(0), getResources().getString(R.string.PLACES_API_KEY));
            posterPhotoRequest.setImgHeight(Constants.POSTER_IMAGE_HEIGHT);
            url = posterPhotoRequest.createRequestWithHeight();
        } else {
            url = mPlaceDetails.getPhotoUrl();
        }
        if(url!=null){
            LogUtil.d(TAG, "url -->" + url);
            mPosterImage.setVisibility(View.VISIBLE);
            mPosterImage.setContentDescription(R.string.reader_text_poster+mPlace.getName());
            Glide.with(getContext()).load(url).placeholder(R.drawable.loading).error(R.drawable.error).into(mPosterImage);
        }
    }

    private String extractUrlFromLink(String mapLink) {
        mapLink = mapLink.replace(Constants.HYPERLINK_SUB_STRING_START,"");
        int endIndex = mapLink.indexOf(Constants.HYPERLINK_SUB_STRING_END);
        mapLink = mapLink.substring(0,endIndex-1);
        return mapLink;
    }


}
