package varun.com.studentmanagementsystemsample.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.adapter.GalleryAdapter;
import varun.com.studentmanagementsystemsample.bean.AlbumBean;
import varun.com.studentmanagementsystemsample.bean.GalleryBean;
import varun.com.studentmanagementsystemsample.bean.ImagesBean;
import varun.com.studentmanagementsystemsample.constants.Api;
import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

/**
 * Created by Varun on 4/2/2016.
 */
public class GalleryFragment extends Fragment {

    RecyclerView galleryRecyclerView;
    GalleryAdapter adapter;
    ArrayList<GalleryBean> list;

    String sampleImageJSON = "{\n" +
            "    \"ImageGalleryResult\": [\n" +
            "        {\n" +
            "            \"albumID\": 1,\n" +
            "            \"albumTitle\": \"Picnic\",\n" +
            "            \"albumDescription\": \"Picnic\",\n" +
            "            \"albumCreationDate\": \"2016-06-23\",\n" +
            "            \"schoolID\": 1,\n" +
            "            \"images\": [\n" +
            "                {\n" +
            "                    \"imageID\": 2,\n" +
            "                    \"imageTitle\": \"pic1\",\n" +
            "                    \"imageDescription\": \"pic1\",\n" +
            "                    \"imageTakenDate\": \"1894-06-24\",\n" +
            "                    \"imagePath\": \"/ImageGallery/school_1/picnic/pic1.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"imageID\": 3,\n" +
            "                    \"imageTitle\": \"pic2\",\n" +
            "                    \"imageDescription\": \"pic2\",\n" +
            "                    \"imageTakenDate\": \"1894-06-24\",\n" +
            "                    \"imagePath\": \"/ImageGallery/school_1/picnic/pic2.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"albumID\": 2,\n" +
            "            \"albumTitle\": \"Annual Day\",\n" +
            "            \"albumDescription\": \"Annual Day\",\n" +
            "            \"albumCreationDate\": \"2016-07-06\",\n" +
            "            \"schoolID\": 1,\n" +
            "            \"images\": [\n" +
            "                {\n" +
            "                    \"imageID\": 4,\n" +
            "                    \"imageTitle\": \"pic3\",\n" +
            "                    \"imageDescription\": \"pic3\",\n" +
            "                    \"imageTakenDate\": \"1894-06-24\",\n" +
            "                    \"imagePath\": \"ImageGallery/school_1/annualday/annual1.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"imageID\": 5,\n" +
            "                    \"imageTitle\": \"pic4\",\n" +
            "                    \"imageDescription\": \"pic4\",\n" +
            "                    \"imageTakenDate\": \"2016-07-06\",\n" +
            "                    \"imagePath\": \"/ImageGallery/school_1/annualday/annual2.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"imageID\": 6,\n" +
            "                    \"imageTitle\": \"pic5\",\n" +
            "                    \"imageDescription\": \"pic5\",\n" +
            "                    \"imageTakenDate\": \"2016-07-06\",\n" +
            "                    \"imagePath\": \"/ImageGallery/school_1/annualday/annual3.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"imageID\": 7,\n" +
            "                    \"imageTitle\": \"pic6\",\n" +
            "                    \"imageDescription\": \"pic6\",\n" +
            "                    \"imageTakenDate\": \"2016-07-06\",\n" +
            "                    \"imagePath\": \"/ImageGallery/school_1/annualday/annual4.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"StatusCode\": 0,\n" +
            "    \"Message\": \"Sucess\"\n" +
            "}";

    InputStream inputStream;
    StringBuilder stringBuilder;
    String imageGalleryResponse;

    SessionManager sessionManager;
    ProgressDialog progressDialog;

    ArrayList<AlbumBean> albumList;
    ArrayList<ImagesBean> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        sessionManager = new SessionManager(GalleryFragment.this.getActivity());

        galleryRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvGallery);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        new ForImageGallery().execute();
    }

    class ForImageGallery extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            albumList = new ArrayList<>();
            imageList = new ArrayList<>();
            progressDialog = new ProgressDialog(GalleryFragment.this.getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JSONStringer eventJsonStringer = new JSONStringer();

            try {

                String schoolID = null;

                if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_PARENT) {

                    schoolID = "" + sessionManager.getUserDetails().getSchoolID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_STUDENT) {

                    schoolID = "" + sessionManager.getUserDetails().getSchoolID();

                } else if (sessionManager.getUserDetails().getUserTypeID() == Constants.USER_TYPE_TEACHER) {

                    schoolID = "" + sessionManager.getUserDetails().getSchoolID();
                }

                eventJsonStringer.object().key(Constants.KEY_SCHOOL_ID).value("1").endObject();

                URL url = new URL(Api.IMAGE_GALLERY_URL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-type",
                        "application/json");
                conn.setRequestProperty("Accept",
                        "application/json");

                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        os, "UTF-8"));
                writer.write(eventJsonStringer.toString());

                writer.flush();
                writer.close();
                os.close();

                inputStream = conn.getErrorStream();
                if (inputStream == null) {
                    inputStream = conn.getInputStream();
                }

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream), 1000);
                stringBuilder = new StringBuilder();
                stringBuilder.append(reader.readLine() + "\n");

                String line = "0";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                inputStream.close();
                imageGalleryResponse = stringBuilder.toString();

            } catch (Exception e) {
                Log.e("EVENT ERROR: ", "EVENT ERROR: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {

                String albumID = null, albumTitle = null, albumDescription = null, albumCreationDate = null;
                String imageID = null, schoolID = null, imageTitle = null, imageDescription = null, imageTakenDate = null, imagePath = null;

                JSONObject root = new JSONObject(imageGalleryResponse);

                JSONArray albumArray = root.getJSONArray(Constants.KEY_IMAGE_GALLERY_RESULT);
                for (int i = 0; i < albumArray.length(); i++) {
                    JSONObject albumObject = albumArray.getJSONObject(i);

                    albumID = albumObject.getString(Constants.KEY_ALBUM_ID);
                    albumTitle = albumObject.getString(Constants.KEY_ALBUM_TITLE);
                    albumDescription = albumObject.getString(Constants.KEY_ALBUM_DESCRIPTION);
                    albumCreationDate = albumObject.getString(Constants.KEY_ALBUM_CREATION_DATE);
                    schoolID = albumObject.getString(Constants.KEY_SCHOOL_ID);

                    JSONArray imageArray = albumObject.getJSONArray(Constants.KEY_IMAGES);
                    for (int j = 0; j < imageArray.length(); j++) {
                        JSONObject imageObject = imageArray.getJSONObject(j);

                        imageID = imageObject.getString(Constants.KEY_IMAGE_ID);
                        imageTitle = imageObject.getString(Constants.KEY_IMAGE_TITLE);
                        imageDescription = imageObject.getString(Constants.KEY_IMAGE_DESCRIPTION);
                        imageTakenDate = imageObject.getString(Constants.KEY_IMAGE_TAKEN_DATE);
                        imagePath = imageObject.getString(Constants.KEY_IMAGE_PATH);

                        imageList.add(new ImagesBean(imageID, imageTitle, imageDescription, imageTakenDate, imagePath));
                    }

                    albumList.add(new AlbumBean(albumID, albumTitle, albumDescription, albumCreationDate, imageList, schoolID));
                }

                adapter = new GalleryAdapter(GalleryFragment.this.getActivity(), albumList);

                galleryRecyclerView.setAdapter(adapter);
                galleryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressDialog.dismiss();
        }
    }
}
