package com.example.jennifer.chat;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by jennifer on 19/04/2017.
 */

public class MessageAdapter extends ArrayAdapter<Message> {

    private List<Message> messages;
    Context mContext;

    private static class ViewHolder {
        TextView name;
        TextView body;
        TextView time;
    }

    public MessageAdapter(List<Message> objects, Context context) {
        super(context, R.layout.message, objects);
        if(objects == null) {
            return;
        }

        this.messages = objects;
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.message, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.body = (TextView) convertView.findViewById(R.id.body);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        long time = message.getTimestamp();

        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.setTimeInMillis(time);
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date currenTimeZone = (Date) calendar.getTime();

        viewHolder.name.setText(message.getAuthor());
        viewHolder.body.setText(message.getBody());
        viewHolder.time.setText(sdf.format(currenTimeZone));

        return convertView;

    }


}
