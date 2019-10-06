package com.example.mushr.colorfool.F3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import com.example.mushr.colorfool.Utils.DBUtil;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class CardDao {

    private DBUtil dbUtil;
    private SQLiteDatabase db;

    public CardDao(Context context) {
        dbUtil = new DBUtil(context);
    }
    /**
     * 从本地数据库获取数据
     *
     * @return
     */
    public ArrayList<CardBean> getItemList() {

        ArrayList<CardBean> itemList = new ArrayList<CardBean>();
        try {

            //  通过调用getWritableDatabase方法获取一个可以读写数据库的对象
            db = dbUtil.getWritableDatabase();
            //  游标，类似JavaWeb中的resultset
            Cursor cursor = db.rawQuery("select * from cards", null);

            //  查询之前要先判断数据库中是否有数据
            if (cursor.moveToFirst()) {
                do {

                    //  实例化一个bean，也就是VO
                    CardBean bean = new CardBean();

                    String imageId = cursor.getString(cursor.getColumnIndex("imageId"));
                    String userId = cursor.getString(cursor.getColumnIndex("userId"));
                    String userName = cursor.getString(cursor.getColumnIndex("userName"));
//                  // picture
                    byte[] pic1 = cursor.getBlob(cursor.getColumnIndex("image"));
                    ByteArrayInputStream bin1 = new ByteArrayInputStream(pic1);
                    // userhead
                    byte[] pic2 = cursor.getBlob(cursor.getColumnIndex("userHead"));
                    ByteArrayInputStream bin2 = new ByteArrayInputStream(pic2);

                    bean.setImageId(imageId);
                    bean.setUserId(userId);
                    bean.setUserName(userName);
                    bean.setImage(Drawable.createFromStream(bin1, "pic1"));
                    bean.setUserHead(Drawable.createFromStream(bin2, "pic2"));

                    itemList.add(bean);

                    //这句话是调试用的，把select到的信息输出到Logcat
                    //System.out.println(rank + "-" + name);
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return itemList;
    }
}