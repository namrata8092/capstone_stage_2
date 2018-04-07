package com.nds.pmc.converter;

import com.nds.pmc.common.Constants;
import com.nds.pmc.model.PlaceDetails;
import com.nds.pmc.model.ReviewDetails;
import com.nds.pmc.tos.responses.DetailResponse;
import com.nds.pmc.tos.responses.DetailedSearchResult;
import com.nds.pmc.tos.responses.PhotoObject;
import com.nds.pmc.tos.responses.ReviewObject;
import com.nds.pmc.util.JSONSerializeHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Namrata Shah on 3/1/2018.
 */

public class SearchDetailResponseConverter {

    public SearchDetailResponseConverter() {
    }

    public static PlaceDetails getSearchDetailResultModel(String response) {
        DetailResponse detailResponse = JSONSerializeHelper.deserializeObject(DetailResponse.class, response);
        if (detailResponse.getStatus().equalsIgnoreCase(Constants.OK_STATUS) && detailResponse.getResult() != null) {
            DetailedSearchResult result = detailResponse.getResult();
            PlaceDetails placeDetails = new PlaceDetails(result.getPlaceID(), result.getName(), result.getFormattedAddress());

            placeDetails.setRating(result.getRating());
            placeDetails.setPhotoRawData(result.getReference());
            placeDetails.setPhotoUrl(result.getIcon());

            if (result.getISDPhoneNumber() != null)
                placeDetails.setPhoneNumber(result.getISDPhoneNumber());

            if (result.getWebsite() != null)
                placeDetails.setWebSiteUrl(result.getWebsite());

            if (result.getOpeningHours() != null) {
                placeDetails.setOpen(result.getOpeningHours().isOpenNow());
                if (result.getOpeningHours().getWeekdayText() != null && !result.getOpeningHours().getWeekdayText().isEmpty()) {
                    placeDetails.setWeeklyTiming(convertToWeeklyTiming(result.getOpeningHours().getWeekdayText()));
                }
            }

            if (result.getPhotos() != null && !result.getPhotos().isEmpty())
                placeDetails.setPhotoList(convertToPhotoList(result.getPhotos()));


            if(result.getReviews()!=null && !result.getReviews().isEmpty())
                placeDetails.setReviewDetailList(convertToReviewModel(result.getReviews()));

            return placeDetails;
        }
        return null;
    }

    private static List<ReviewDetails> convertToReviewModel(List<ReviewObject> reviews) {
        List<ReviewDetails> reviewList = new ArrayList<>();
        for(ReviewObject reviewObject : reviews){
            ReviewDetails reviewDetails = new ReviewDetails(reviewObject.getAuthorName(), reviewObject.getRating(), reviewObject.getText());
            reviewDetails.setRelativeTime(reviewObject.getRelativeTimeDescription());
            reviewDetails.setReviewerPhotoRawData(reviewObject.getProfilePhotoURL());
            reviewList.add(reviewDetails);
        }

        return reviewList;
    }

    private static List<String> convertToPhotoList(List<PhotoObject> photos) {
        List<String> photoList = new ArrayList<>();
        for (PhotoObject photoObject : photos) {
            if (photoObject.getPhotoReference() != null)
                photoList.add(photoObject.getPhotoReference());
        }
        return photoList;
    }

    private static String convertToWeeklyTiming(List<String> weekdayText) {
        StringBuilder weekText = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < weekdayText.size(); i++) {
            weekText.append(weekdayText.get(i)).append(Constants.NEW_LINE_CHARACTER);
        }
        return weekText.toString();
    }
}
