package com.shc.north;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.neusoft.library.base.ListItemCallback;
import com.neusoft.library.base.SimpleListAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class SmsAdapter extends SimpleListAdapter<SmsBody, SmsAdapter.ViewHolder> {
    public SmsAdapter(Context context, List<SmsBody> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_sms;
    }

    @Override
    protected void convert(ViewHolder holder, SmsBody item, int position) {
        holder.tvNumber.setText(item.getSourcePhone());
        holder.tvContent.setText(item.getContent());
        holder.tvDate.setText(Utils.msgTime(Utils.getTimeS(item.getSendTime())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDetailAct(item);
            }
        });
    }

    private void startDetailAct(SmsBody smsBean){
        Intent intent = new Intent(context, SmsDetailAct.class);
        intent.putExtra("sms", smsBean);
        context.startActivity(intent);
    }

    public void addItem(SmsBody bean){
        this.data.add(0, bean);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        View itemView;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_content)
        TextView tvContent;
        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
