package com.nds.pmc.tos.requests;

import android.net.Uri;

import com.nds.pmc.common.Constants;

/**
 * Created by Namrata on 2/27/2018.
 */

public class PosterPhotoRequest {
    private String imgWidth;
    private String imgHeight;
    private final String imgRowData;
    private final String apiKey;

    public PosterPhotoRequest(String rowdata, String key){
        this.imgRowData = rowdata;
        this.apiKey = key;
    }

    public String createRequest(){
        Uri.Builder uri = Uri.parse(Constants.IMAGE_BASE_URL).buildUpon();
        uri.appendQueryParameter(Constants.IMG_REFERENCE_KEY,imgRowData)
                .appendQueryParameter(Constants.API_KEY_KEY, apiKey).build();
        return  uri.toString();
    }

    public String createRequestWithHeightNWidth(){
        Uri.Builder uri = Uri.parse(Constants.IMAGE_BASE_URL).buildUpon();
        uri.appendQueryParameter(Constants.IMG_WIDTH_KEY, imgWidth)
                .appendQueryParameter(Constants.IMG_HEIGHT_KEY, imgHeight)
                .appendQueryParameter(Constants.IMG_REFERENCE_KEY,imgRowData)
                .appendQueryParameter(Constants.API_KEY_KEY, apiKey).build();
        return  uri.toString();
    }

    public String createRequestWithHeight(){
        Uri.Builder uri = Uri.parse(Constants.IMAGE_BASE_URL).buildUpon();
        uri.appendQueryParameter(Constants.IMG_HEIGHT_KEY, imgHeight)
                .appendQueryParameter(Constants.IMG_REFERENCE_KEY,imgRowData)
                .appendQueryParameter(Constants.API_KEY_KEY, apiKey).build();
        return  uri.toString();
    }

    public String createRequestWithWidth(){
        Uri.Builder uri = Uri.parse(Constants.IMAGE_BASE_URL).buildUpon();
        uri.appendQueryParameter(Constants.IMG_WIDTH_KEY, imgWidth)
                .appendQueryParameter(Constants.IMG_REFERENCE_KEY,imgRowData)
                .appendQueryParameter(Constants.API_KEY_KEY, apiKey).build();
        return  uri.toString();
    }

    public String getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getImgRowData() {
        return imgRowData;
    }

    public String getApiKey() {
        return apiKey;
    }
}
