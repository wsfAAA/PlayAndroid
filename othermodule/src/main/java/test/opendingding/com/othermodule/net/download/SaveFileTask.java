package test.opendingding.com.othermodule.net.download;

import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;
import test.opendingding.com.othermodule.net.FileUtil;
import test.opendingding.com.othermodule.net.callback.IRequest;
import test.opendingding.com.othermodule.net.callback.ISuccess;

/**
 * Created by 傅令杰 on 2017/4/2
 */

final class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0]; //下载目录
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    /**
     * 启动apk
     *
     * @param file
     */
    private void autoInstallApk(File file) {
//        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
//            final Intent install = new Intent();
//            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            install.setAction(Intent.ACTION_VIEW);
//            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//            startActivity(install);
//        }
    }
}
