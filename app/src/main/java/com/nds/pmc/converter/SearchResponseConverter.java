package com.nds.pmc.converter;

import com.nds.pmc.model.PhotoModel;
import com.nds.pmc.model.Place;
import com.nds.pmc.model.PlacesSearchResult;
import com.nds.pmc.tos.responses.PhotoObject;
import com.nds.pmc.tos.responses.SearchResult;
import com.nds.pmc.tos.responses.BaseResponse;
import com.nds.pmc.util.JSONSerializeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Namrata on 11/13/2017.
 */

public class SearchResponseConverter {

    public SearchResponseConverter(){}

    public static PlacesSearchResult getSearchResultModel(String response){
        BaseResponse baseResponse = JSONSerializeHelper.deserializeObject(BaseResponse.class, response);
        PlacesSearchResult searchResultModel = new PlacesSearchResult(baseResponse.getNextPageToken(),
                convertToPlacesModel(baseResponse.getResults()));
        return searchResultModel;
    }

    private static List<Place> convertToPlacesModel(List<SearchResult> results) {
        if(results == null || results.isEmpty())
            return null;
        List<Place> places = new ArrayList<>();
        for(int resultIndex = 0; resultIndex < results.size(); resultIndex++){
            SearchResult search = results.get(resultIndex);
            Place place = new Place(Double.toString(search.getGeometry().getLocation().getLat()),
                    Double.toString(search.getGeometry().getLocation().getLng()), search.getName());
            place.setOpenNow(search.getOpeningHours().isOpenNow());
            place.setId(search.getPlaceId());
            place.setRating(search.getRating());
            place.setAddress(search.getVicinity());
            place.setPhotos(convertToPhotoList(search.getPhotos()));
            places.add(place);
        }
        return places;
    }

    private static List<PhotoModel> convertToPhotoList(List<PhotoObject> photos) {
        if(photos == null || photos.isEmpty())
            return null;
        List<PhotoModel> photoModels = new ArrayList<>();
        for(int photoIndex = 0; photoIndex < photos.size(); photoIndex++){
            PhotoObject photo = photos.get(photoIndex);
            PhotoModel photoModel = new PhotoModel(Double.toString(photo.getWidth()),
                    Double.toString(photo.getHeight()));
            photoModel.setMapLink(photo.getHtmlAttributions().get(0));
            photoModel.setImageRaw(photo.getPhotoReference());
            photoModels.add(photoModel);
        }
        return photoModels;
    }

}
