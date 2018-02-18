package com.nds.pmc.views.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nds.pmc.R;
import com.nds.pmc.common.Constants;
import com.nds.pmc.model.Place;
import com.nds.pmc.services.FavoritePlaceUpdateListener;
import com.nds.pmc.services.UpdateFavoritePlaceToDB;
import com.nds.pmc.util.DeviceUtil;

/**
 * Created by Namrata on 1/11/2018.
 */

public class SearchResultDetailFragment extends Fragment implements OnMapReadyCallback, FavoritePlaceUpdateListener {
    private Place mPlace;
    private MapView mMapView;
    private TextView mAddToFavorite;
    private SharedPreferences mSharedPreferences;

    public static SearchResultDetailFragment newInstance(Place place) {
        SearchResultDetailFragment fragment = new SearchResultDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY, place);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(Constants.PLACE_DETAIL_BUNDLE_KEY)) {
            mPlace = savedInstanceState.getParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY);
        } else if(getArguments()!=null){
            mPlace = getArguments().getParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY);
        }
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_detail, container, false);
        if(mPlace!=null) {
            setPostImage(rootView);

            setPlaceTitleAddress(rootView);

            setPlaceOperationHours(rootView);

            setPlaceMap(rootView, savedInstanceState);

            setFavoritePlace(rootView);
        }
        return rootView;
    }

    private void setFavoritePlace(View rootView) {
        mAddToFavorite = (TextView) rootView.findViewById(R.id.addToFavorite);
        if(mPlace.isWidgetEntry()){
            mAddToFavorite.setVisibility(View.GONE);
            return;
        }

        if(mSharedPreferences.getBoolean(mPlace.getId(), false)){
            mAddToFavorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.favorite_on, 0);
        }else{
            mAddToFavorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.favorite_off, 0);
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

    @SuppressLint("MissingPermission")
    private void setPlaceMap(View rootView, Bundle savedInstanceState) {
        mMapView = (MapView) rootView.findViewById(R.id.placeLocation);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }

    private void setPlaceOperationHours(View rootView) {
        TextView openSymbol = (TextView) rootView.findViewById(R.id.openSymbol);
        LinearLayout hourContainer = (LinearLayout) rootView.findViewById(R.id.timingContainer);
        TextView openAt = (TextView) rootView.findViewById(R.id.openAt);
        TextView closeAt = (TextView) rootView.findViewById(R.id.closeAt);
        if (mPlace.isOpenNowDetails()) {
            openSymbol.setText(mPlace.isOpenNow() ? getString(R.string.open_now) : getString(R.string.close_now));
            if (mPlace.isOpeningHours()) {
                openAt.setText(getString(R.string.open_at) + mPlace.getOpenAt());
                closeAt.setText(getString(R.string.close_at) + mPlace.getCloseAt());
            } else
                hourContainer.setVisibility(View.GONE);
        } else
            openSymbol.setVisibility(View.GONE);

    }

    private void setPlaceTitleAddress(View rootView) {
        TextView placeTitle = (TextView) rootView.findViewById(R.id.placeTitle);
        TextView placeAddress = (TextView) rootView.findViewById(R.id.placeAddress);
        RatingBar resultRating = (RatingBar) rootView.findViewById(R.id.resultRating);


        placeTitle.setText(mPlace.getName());
        placeAddress.setText(DeviceUtil.getAddress(mPlace.getLongitude(), mPlace.getLatitude(), getContext()));
        resultRating.setRating((float) mPlace.getRating());

    }

    private void setPostImage(View rootView) {
        ImageView posterImage = (ImageView) rootView.findViewById(R.id.posterImage);
        TextView imageLink = (TextView) rootView.findViewById(R.id.imageLink);
        if (mPlace.getPhotos() != null && !mPlace.getPhotos().isEmpty() && mPlace.getPhotos().get(0).getImageRaw() != null) {
            imageLink.setText(Html.fromHtml(mPlace.getPhotos().get(0).getMapLink()));
            String imageRaw = mPlace.getPhotos().get(0).getImageRaw();
            try {
                byte[] byteArray = Base64.decode(imageRaw, Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                posterImage.setImageBitmap(bmp);
            } catch (Exception e) {
                posterImage.setVisibility(View.GONE);
            }
        } else {
            imageLink.setVisibility(View.GONE);
            posterImage.setVisibility(View.GONE);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(Constants.PLACE_DETAIL_BUNDLE_KEY, mPlace);
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
        googleMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .position(new LatLng(mPlace.getLatitude(), mPlace.getLongitude())));
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setMyLocationEnabled(true);
        MapsInitializer.initialize(this.getActivity());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(mPlace.getLatitude(), mPlace.getLongitude()), 10);
        googleMap.animateCamera(cameraUpdate);
    }

    @Override
    public void onSuccess(final int status) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (status == Constants.ADDED_TO_FAVORITE) {
                    mAddToFavorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.favorite_on, 0);
                    mSharedPreferences.edit().putBoolean(mPlace.getId(), true).apply();
                } else {
                    mAddToFavorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.favorite_off, 0);
                    mSharedPreferences.edit().putBoolean(mPlace.getId(), false).apply();
                }
            }
        });
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), "Can not add place to favorite", Toast.LENGTH_SHORT).show();
    }
}
