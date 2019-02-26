package au.com.sports.mate.test.stickylist;

import android.content.Context;
import android.service.autofill.FieldClassification;
import android.support.v4.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import au.com.sports.mate.test.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private final List<StickyListActivity.FavTeamData> data;
    private final List<StickyListActivity.Artcles> artcles = new ArrayList<>();
    private String[] countries;
    private LayoutInflater inflater;
    private long[] headerIdArray;

    public StickyListAdapter(Context context, List<StickyListActivity.FavTeamData> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        parseData(data, this.data, this.artcles);
//        generateHeadersId(data);
    }

    private void parseData(List<StickyListActivity.FavTeamData> data, List<StickyListActivity.FavTeamData> data1, List<StickyListActivity.Artcles> artcles) {

        for(StickyListActivity.FavTeamData teamData : data) {
            for(StickyListActivity.Artcles teamArticles :teamData.getArticles())
                artcles.add(new StickyListActivity.Artcles(teamArticles.title, teamArticles.subTitle));
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_article, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.title);
            holder.text1 = (TextView) convertView.findViewById(R.id.subheading);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        List<StickyListActivity.Artcles> item = data.get(position).getArticles();

        for(StickyListActivity.Artcles artclesList : item) {
            holder.text.setText(artclesList.title);
            holder.text1.setText(artclesList.subTitle);
        }

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.heading);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = data.get(position).title;
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon

//        long id = (position >= 0 && position < data.size()) ? headerIdArray[position] : 0;
//        return id;
        String s[] = data.get(position).getTitle().split("\\s");

        return s[0].charAt(1) + s[1].charAt(0);
//        return data.get(position).getTitle().split("\\s").length;
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
        TextView text1;
    }

}