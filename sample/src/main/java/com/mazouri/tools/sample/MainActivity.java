package com.mazouri.tools.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazouri.tools.Tools;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler= (RecyclerView) findViewById(R.id.recycler);

        GridLayoutManager layoutManager=new GridLayoutManager(this, 3);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.addItemDecoration(new SpacesItemDecoration(15));
        mRecycler.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView=getLayoutInflater().inflate(R.layout.item_layout,parent,false);
                return new ViewHolder(itemView);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                holder.tv.setText(TOOLS[position]);
                holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tools.toast().showToast(MainActivity.this, "you click " + TOOLS[position]);

                    }
                });
            }

            @Override
            public int getItemCount() {
                return TOOLS.length;
            }
        });
    }

    final static class ViewHolder extends RecyclerView.ViewHolder{

        public View itemLayout;
        public TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            itemLayout= itemView.findViewById(R.id.itemLayout);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    private static final String[] TOOLS=new String[]{
            "App",
            "Log",
            "String",
            "Apk",
            "Shell",
            "Time",
            "Snackbar",
            "Toast",
            "Device",
            "Network",
            "Unit",
            "SP",
            "InputMethod",
            "Intent",
            "Process",
            "Constants",
            "Convert",
            "Bitmap",
            "Close",
            "ExternalStorage",
            "File",
            "Regex",
            "SecureAES",
            "SecureBase64",
            "SecureDES3",
            "SecureDES",
            "SecureHex",
            "SecureMD5",
            "SecureRSA",
            "Secure"
    };

}
