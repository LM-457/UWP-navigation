package uwp.cs.edu.parkingtracker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import uwp.cs.edu.parkingtracker.parking.ParkingZoneOption;

/**
 * Created by Russ on 4/21/2015.
 */
public class ParkingZoneOptionAdapter extends ArrayAdapter<ParkingZoneOption> {
    Context context;
    int layoutResourceId;
    List<ParkingZoneOption> data = null;
//    ParkingZoneOption data[] = null;

    public ParkingZoneOptionAdapter(Context context, int layoutResourceId, List<ParkingZoneOption> data) {
        super(context,layoutResourceId,data);
        this.layoutResourceId=layoutResourceId;
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ParkingZoneOptionHolder holder = null;

        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row=inflater.inflate(layoutResourceId,parent,false);
            holder=new ParkingZoneOptionHolder();
            holder.txtTitle=(TextView)row.findViewById(R.id.txtTitle);
            holder.txtDistance=(TextView)row.findViewById(R.id.txtDistance);
            holder.txtFullness=(TextView)row.findViewById(R.id.txtFullness);
            row.setTag(holder);
        }
        else{
            holder = (ParkingZoneOptionHolder)row.getTag();
        }

        ParkingZoneOption parkingZoneOption = data.get(position);

        String distance = String.format("%.0f",parkingZoneOption.distance);
        String strFullness = "";
        int fullnessColor = 0;
        if (Double.valueOf(parkingZoneOption.zone.getFullness()) > 6.66) {
            strFullness="Full";
            fullnessColor = Color.RED;
        }
        if (Double.valueOf(parkingZoneOption.zone.getFullness()) >= 3.33 && Double.valueOf(parkingZoneOption.zone.getFullness()) <= 6.66) {
            strFullness="Half Full";
            fullnessColor = Color.YELLOW;
        }
        if (Double.valueOf(parkingZoneOption.zone.getFullness()) < 3.33) {
            strFullness="Empty";
            fullnessColor = Color.GREEN;
        }
        String strDistance = String.format("%.0fm",parkingZoneOption.distance);
        holder.txtTitle.setText(parkingZoneOption.title);

        holder.txtDistance.setText(strDistance);
        holder.txtFullness.setText(strFullness);
        holder.txtFullness.setTextColor(fullnessColor);

        return row;
    }

    static class ParkingZoneOptionHolder{
        TextView txtTitle;
        TextView txtDistance;
        TextView txtFullness;
    }

}
