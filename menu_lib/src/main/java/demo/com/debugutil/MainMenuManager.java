package demo.com.debugutil;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;

import com.zg.android_utils.util_common.BaseUtil;
import com.zg.android_utils.util_common.SDCardUtils;
import com.zg.android_utils.util_common.ToastUtil;
import com.zg.android_utils.util_common.WindowUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import demo.com.debugutil.customview.CustomPopupWindow;

/**
 * @author ZhaoKeqiang
 * @date 2018/7/4
 * TODO:
 */
public class MainMenuManager {
    public static void setupMainMenu(final View anchor) {
        anchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainMenu(anchor);
            }
        });
    }

    private static void showMainMenu(final View anchor) {
        final CustomPopupWindow dropdownMenu = new CustomPopupWindow();
        if (BuildConfig.DEBUG) {
            dropdownMenu.addMenuItem("DEBUG", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDebugMenu(anchor);
                }
            });
        }
        dropdownMenu.addMenuItem("修改服务器设置", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
//                startActivity(EditServerActivity.class);
            }
        });

        dropdownMenu.addMenuItem("关于", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
//                startActivity(AboutActivity.class);
            }
        });
        dropdownMenu.addMenuItem("退出", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                BaseUtil.killProcess();
            }
        });
        dropdownMenu.showAsDropDown(anchor, 0, WindowUtils.dp2Px(BaseUtil.getContext(), -4));
    }

    private static void showDebugMenu(final View anchor) {
        final CustomPopupWindow dropdownMenu = new CustomPopupWindow();

        dropdownMenu.addMenuItem("设备列表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
//                startActivity(DeviceListActivity.class);
            }
        });
        dropdownMenu.addMenuItem("查看崩溃日志", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                openDir(anchor, new File(SDCardUtils.getCrashReportPath()));
            }
        });
        dropdownMenu.addMenuItem("查看程序日志", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDir(anchor, BaseConfig.LOG_DIR);
            }
        });
        dropdownMenu.addMenuItem("查看性能日志", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDir(anchor, BaseConfig.PERFORMANCE_LOG_DIR);
            }
        });
        dropdownMenu.addMenuItem("查看启车日志", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDir(anchor, BaseConfig.START_AND_STOP_LOG_DIR);
            }
        });
//        dropdownMenu.addMenuItem("websocket test", new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StompMessageClient.subscribe("/topic/test", new StompMessageClient.OnMessageListener() {
//                    @Override
//                    public void onMessage(String msg) {
//                        LogUtil.i("websocket", msg);
//                    }
//                });
//            }
//        });
//        if (!Config.PRODUCTION) {
//            dropdownMenu.addMenuItem("新界面", new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(MainActivity.class);
//                }
//            });
//            dropdownMenu.addMenuItem("压滤Debug", new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(FilterPressDebugActivity.class);
//                }
//            });
//        }
        dropdownMenu.showAtLocation(anchor, Gravity.CENTER, 0, 0);
    }

    private static void startActivity(Class<?> activity) {
        Intent intent = new Intent(BaseUtil.getContext(), activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseUtil.getContext().startActivity(intent);
    }

    private static void openDir(View v, final File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            ToastUtil.showToast("没有文件");
            return;
        }
        final CustomPopupWindow dropdownWindow = new CustomPopupWindow();
        dropdownWindow.setTitle("清空日志").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                /*final CustomDialog dialog = new CustomDialog(getMainActivity());
                dialog.setCanceledOnTouchOutside(false);
                dialog.setTitle("");
                dialog.showDividerLine(false);
                dialog.setMessage("确定要清空日志吗？");
                dialog.addCancelButton();
                dialog.addButton(R.string.queding, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDir(dir);
                        dialog.dismiss();
                        dropdownWindow.dismiss();
                    }
                });
                dialog.show();*/
            }
        });
        dropdownWindow.setTitle("所在目录：" + dir.getAbsolutePath()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDir(dir);
            }
        });
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o2.getName().compareTo(o1.getName());
            }
        });
        for (final File file : files) {
            dropdownWindow.addMenuItem(file.getName(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTextFile(file);
                    dropdownWindow.dismiss();
                }
            });
        }
        dropdownWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
    }

    private static void openTextFile(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri2 = Uri.fromFile(file);
        intent.setDataAndType(uri2, "text/plain");
        BaseUtil.getContext().startActivity(intent);
    }

    private static void openDir(File dir) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(dir.getAbsolutePath());
        intent.setDataAndType(uri, "*/*");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseUtil.getContext().startActivity(intent);
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
