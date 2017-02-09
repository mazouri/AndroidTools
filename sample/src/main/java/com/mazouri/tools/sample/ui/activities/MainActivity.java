package com.mazouri.tools.sample.ui.activities;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.daijia.library.FABBaseDialog;
import com.mazouri.tools.Tools;
import com.mazouri.tools.sample.R;
import com.mazouri.tools.sample.listener.IViewItemItemListener;
import com.mazouri.tools.sample.model.Category;
import com.mazouri.tools.sample.model.Info;
import com.mazouri.tools.sample.ui.MainAdapter;
import com.mazouri.tools.sample.ui.MaterialDialogCustomContent;

import java.util.ArrayList;

import kr.co.namee.permissiongen.PermissionGen;

public class MainActivity extends AppCompatActivity implements IViewItemItemListener {

    private static final java.lang.String TAG = "MainActivity";
    private RecyclerView mRecycler;
    private MainAdapter adapter;
    private ArrayList<Category> categories = new ArrayList<>();
    private MainProducer mainProducer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkNeedPermissions();

        mainProducer = new MainProducer(this);

        initialData();
        initialRecycler();
    }

    private void initialRecycler() {
        mRecycler= (RecyclerView) findViewById(R.id.recycler);
        adapter = new MainAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecycler.setLayoutManager(manager);
        adapter.setLayoutManager(manager);
        adapter.setCategories(categories);
        mRecycler.setAdapter(adapter);
        adapter.setIViewItemListener(this);
    }

    private FABBaseDialog fabBaseDialog;

    @Override
    public void onItemClickListener(View v, int position, int whichPos) {
        Info currentItem = adapter.getCurrentItem(position, whichPos);
        Tools.log().d(TAG, "onItemClickListener currentItem : " + currentItem.getTitle());

        switch (currentItem.getResourceId()) {
            case R.drawable.ic_android_black_24dp:
                showItemDialog(currentItem, mainProducer.buildSysInfo());
                break;
            case R.drawable.ic_phone_android_black_24dp:
                showItemDialog(currentItem, mainProducer.buildHardwareInfo());
                break;
            case R.drawable.ic_wifi_black_24dp:
                showItemDialog(currentItem, mainProducer.buildNetInfo());
                break;
            case R.drawable.ic_save_black_24dp:
                showItemDialog(currentItem, mainProducer.buildSimInfo());
                break;
            case R.drawable.ic_memory_black_24dp:
                showItemDialog(currentItem, mainProducer.buildCpuInfo());
                break;
            case R.drawable.ic_sd_storage_black_24dp:
                showItemDialog(currentItem, mainProducer.buildSDCardInfo());
                break;
            default:break;
        }
    }

    private void showItemDialog(Info info, String content) {
        MaterialDialogCustomContent dialog = new MaterialDialogCustomContent(this);
        dialog.setTitle(info.getTitle());
        dialog.setIcon(info.getResourceId());
        dialog.setContent(content);
        dialog.show();
    }

    public void initialData() {
        //infos
        ArrayList<Info> infos1 = new ArrayList<>();
        Category category1 = new Category();
        category1.setCategory(CATES[0]);
        for (int i=0; i<INFOS.length;i++) {
            infos1.add(new Info(INFOS[i], INFOIDS[i]));
        }
        category1.setInfos(infos1);

        //settings
        ArrayList<Info> infos2 = new ArrayList<>();
        Category category2 = new Category();
        category2.setCategory(CATES[1]);
        for (int i=0; i<SETTINGS.length;i++) {
            infos2.add(new Info(SETTINGS[i], SETTINGIDS[i]));
        }
        category2.setInfos(infos2);

        //screens
        ArrayList<Info> infos3 = new ArrayList<>();
        Category category3 = new Category();
        category3.setCategory(CATES[2]);
        for (int i=0; i<SCREENS.length;i++) {
            infos3.add(new Info(SCREENS[i], SCREENIDS[i]));
        }
        category3.setInfos(infos3);

        //screens
        ArrayList<Info> infos4 = new ArrayList<>();
        Category category4 = new Category();
        category4.setCategory(CATES[3]);
        for (int i=0; i<TOOLS.length;i++) {
            infos4.add(new Info(TOOLS[i], TOOLIDS[i]));
        }
        category4.setInfos(infos4);

        categories.add(category3);
        categories.add(category1);
        categories.add(category2);
        categories.add(category4);
    }

    private static final String[] CATES=new String[] {
        "设备信息", "设置相关", "屏幕相关", "工具库"
    };

    private static final String[] INFOS = new String[] {
            "系统信息",
            "硬件信息",
            "网络信息",
            "SIM信息",
            "CPU信息",
            "内存储信息",
            "应用信息"
    };

    private static final int[] INFOIDS = new int[] {
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_phone_android_black_24dp,
            R.drawable.ic_wifi_black_24dp,
            R.drawable.ic_save_black_24dp,
            R.drawable.ic_memory_black_24dp,
            R.drawable.ic_sd_storage_black_24dp,
            R.drawable.ic_apps_black_24dp
    };

    private static final String[] SETTINGS = new String[] {
            "系统设置",
            "语言设置",
            "USB调试",
            "开发者选项"
    };

    private static final int[] SETTINGIDS = new int[] {
            R.drawable.ic_settings_black_24dp,
            R.drawable.ic_language_black_24dp,
            R.drawable.ic_usb_black_24dp,
            R.drawable.ic_settings_applications_black_24dp
    };

    private static final String[] SCREENS = new String[] {
            "屏幕信息",
            "标准尺寸"
    };

    private static final int[] SCREENIDS = new int[] {
            R.drawable.ic_aspect_ratio_black_24dp,
            R.drawable.ic_grid_on_black_24dp
    };

    private static final String[] TOOLS = new String[] {
            "图片相关",
            "日志相关",
            "正则表达式",
            "时间相关",
            "字符串相关",
            "单位相关",
            "转换相关",
            "UI相关"
    };

    private static final int[] TOOLIDS = new int[] {
            R.drawable.ic_crop_original_black_24dp,
            R.drawable.ic_format_align_left_black_24dp,
            R.drawable.ic_remove_red_eye_black_24dp,
            R.drawable.ic_access_time_black_24dp,
            R.drawable.ic_sort_by_alpha_black_24dp,
            R.drawable.ic_attach_money_black_24dp,
            R.drawable.ic_swap_horiz_black_24dp,
            R.drawable.ic_format_underlined_black_24dp
    };

    private static final String[] TOOLKIT=new String[]{
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    public void checkNeedPermissions() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.GET_ACCOUNTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .request();
    }
}
