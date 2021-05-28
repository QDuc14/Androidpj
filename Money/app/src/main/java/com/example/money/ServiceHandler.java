package com.example.money;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;


public class ServiceHandler extends AsyncTask<Void, Void, ArrayList> {
    private final static String TITLE = "title";
    private final static String ITEM = "item";
    private final static String LINK = "link";
    private final static String DESCRIPTION = "description";

    private String url;
    private String titleTo;
    private ProgressDialog pDialog;
    private ServiceCaller serviceCaller = new ServiceCaller();

    private CurrencyClass curr;
    private ArrayList<CurrencyClass> lst_curr = new ArrayList<>();

    private Context context;
    private Spinner spinFrom, spinTo;
    private ArrayAdapter<String> adapter;

    public ServiceHandler(Context context, Spinner from, Spinner to, String url, String titleTo){
        this.context = context;
        this.spinFrom = from;
        this.spinTo = to;
        this.url = url;
        this.titleTo = titleTo;
    }

    @Override
    protected ArrayList<CurrencyClass> doInBackground(Void... voids) {
        String str_xml = serviceCaller.call(ServiceCaller.GET, url);
        if(str_xml != null){
            try {
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                fc.setNamespaceAware(true);
                XmlPullParser parser = fc.newPullParser();
                parser.setInput(new StringReader(str_xml));

                int eventType = -1;
                String nodeName = "";
                boolean flag_Item = false;

                while(eventType != XmlPullParser.END_DOCUMENT){
                    eventType = parser.next();
                    nodeName = parser.getName();
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if(nodeName.equalsIgnoreCase(ITEM)){
                                curr = new CurrencyClass();
                                flag_Item = true;
                            }
                            else if(flag_Item){
                                if(nodeName.equalsIgnoreCase(TITLE))
                                    curr.setTitle(formatTitle(parser.nextText()));
                                else if(nodeName.equalsIgnoreCase(LINK))
                                    curr.setLink(formatLink(parser.nextText()));
                                else if(nodeName.equalsIgnoreCase(DESCRIPTION))
                                    curr.setDescription(formatDescription(parser.nextText()));
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if(nodeName.equalsIgnoreCase(ITEM)){
                                lst_curr.add(curr);
                                flag_Item = false;
                            }
                            break;
                    }
                }

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Log.d("XML Data", "Didn't receive any data from server!");
        }
        return lst_curr;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList arr) {
        super.onPostExecute(arr);
        if (pDialog.isShowing())
            pDialog.dismiss();
        if (arr == null)
            Toast.makeText(context, "Lỗi - Refresh lại", Toast.LENGTH_SHORT).show();
        loadAdapter(arr);
    }

    private void loadAdapter(ArrayList<CurrencyClass> lst){
        String[] arr_temp = new String[lst.size()];
        int index = -1;
        int indxTo = -1;
        for(int i=0; i<lst.size(); i++){
            arr_temp[i] = lst.get(i).getTitle();
            if(lst.get(i).getLink().equalsIgnoreCase(url))
                index = i;
            if(!titleTo.equals("") || titleTo != null)
                if(lst.get(i).getTitle().equalsIgnoreCase(titleTo))
                    indxTo = i;
        }
        adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, arr_temp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinFrom.setAdapter(adapter);
        spinTo.setAdapter(adapter);
        spinFrom.setSelection(index);
        spinTo.setSelection(indxTo);
    }

    private String formatTitle(String s){
        return s.split("/")[1];
    }

    private double formatDescription(String s){
        String temp = s.split("=")[1].trim();
        return Double.parseDouble(temp.substring(0, temp.indexOf(" ")));
    }

    private String formatLink(String s){
        return s+"rss.xml";
    }

    public ArrayList<CurrencyClass> getData(){return lst_curr;}
}