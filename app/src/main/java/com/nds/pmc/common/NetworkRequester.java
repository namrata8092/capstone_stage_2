package com.nds.pmc.common;

/**
 * Created by Namrata on 11/8/2017.
 */

public interface NetworkRequester {
    void onFailure(Throwable error);

    void onSuccess(String response);
}
