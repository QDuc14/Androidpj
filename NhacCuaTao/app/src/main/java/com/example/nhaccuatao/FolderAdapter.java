package com.example.nhaccuatao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FolderAdapter extends BaseAdapter {
    private folderActivity context;
    private int layout;
    private ArrayList<Folder> folderlist;

    public FolderAdapter(folderActivity context, int layout, ArrayList<Folder> folderlist) {
        this.context = context;
        this.layout = layout;
        this.folderlist = folderlist;
    }

    @Override
    public int getCount() {
        return folderlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView txtFolderName;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtFolderName      = convertView.findViewById(R.id.txtNameFolder);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Folder  folder = folderlist.get(position);

        holder.txtFolderName.setText(folder.getName());

        return convertView;
    }
}
