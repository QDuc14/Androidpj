package com.example.baitap_3;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;

public class MainActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baitap_3);



        ListView listView = (ListView) findViewById(R.id.listView);

        NamePlace dd1 =  new NamePlace("Nhà sách Nguyễn Văn Cừ");
        NamePlace dd2 =  new NamePlace("Công viên nước Đầm sen");
        NamePlace dd3 =  new NamePlace("Khu du lịch Văn hóa Suối Tiên");
        NamePlace dd4 =  new NamePlace("Trường Đại Học Sài Gòn");
        NamePlace dd5 =  new NamePlace("Nhà hàng Hội Ngộ");
        NamePlace dd6 =  new NamePlace("EON 51- Tầng 51 Bitexco Financial Tower");
        NamePlace dd7 =  new NamePlace("Siêu thị Sài Gòn");

        final NamePlace[] dd = new NamePlace[]{dd1,dd2,dd3,dd4,dd5,dd6,dd7};

        AddressPlace addpl1 = new AddressPlace("241 Đường Nguyễn Văn Cừ, Phường 4, Quận 5, Hồ Chí Minh");
        AddressPlace addpl2 = new AddressPlace("3 Hòa Bình, Phường 3, Quận 11, Hồ Chí Minh");
        AddressPlace addpl3 = new AddressPlace("120 Xa lộ Hà Nội, Phường Tân Phú, Quận 9, Hồ Chí Minh");
        AddressPlace addpl4 = new AddressPlace("273 An Dương Vương, Phường 3, Quận 5, Hồ Chí Minh");
        AddressPlace addpl5 = new AddressPlace("19b Nguyễn Thị Diệu, Phường 6, Quận 3, Hồ Chí Minh");
        AddressPlace addpl6 = new AddressPlace("Cafe Eon, Bitexco Financial Tower, Hải Triều, Bến Nghé, Quận 1, Hồ Chí Minh");
        AddressPlace addpl7 = new AddressPlace("460 Đường 3 Tháng 2, Phường 12, Quận 10, Hồ Chí Minh");

        final AddressPlace[] addpl = new AddressPlace[]{addpl1,addpl2,addpl3,addpl4,addpl5,addpl6,addpl7};

        DiaChiURL dc1 = new DiaChiURL("https://www.sachthanhnghia.com/", "geo:10.768994,106.636312");
        DiaChiURL dc2 = new DiaChiURL("http://www.damsenwaterpark.com.vn/", "geo:10.768994,106.636312");
        DiaChiURL dc3 = new DiaChiURL("https://www.suoitien.com/", "geo:10.768994,106.636312");
        DiaChiURL dc4 = new DiaChiURL("https://sgu.edu.vn/", "geo:10.768994,106.636312");
        DiaChiURL dc5 = new DiaChiURL("http://www.nhahanghoingo.com/", "geo:10.776644, 06.689469");
        DiaChiURL dc6 = new DiaChiURL("https://eon51.com/", "geo:10.771963,106.704759");
        DiaChiURL dc7 = new DiaChiURL("http://www.sieuthisaigon.com.vn/", "geo:10.768623,106.667715");

        final DiaChiURL[] dc = new DiaChiURL[]{dc1,dc2,dc3,dc4,dc5,dc6,dc7};

        ArrayAdapter<NamePlace> arrayAdapter = new ArrayAdapter<NamePlace>(this, android.R.layout.simple_list_item_1 , dd);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(dd[position].getTenDD());
                builder.setCancelable(true);
                builder.setPositiveButton("Map it",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String address = addpl[position].getTenDC();
                                address = address.replaceAll(" ", "+");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
                                mapIntent.setPackage("com.google.android.apps.maps");
                                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(mapIntent);
                                }
                            }
                        });

                builder.setNeutralButton("More info",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent browserIntent = new Intent(
                                        android.content.Intent.ACTION_VIEW,
                                        Uri.parse(dc[position].getTenURL())
                                );
                                startActivity(browserIntent);
                            }
                        });

                AlertDialog alert1 = builder.create();
                alert1.show();
            }
        });
    }
}

