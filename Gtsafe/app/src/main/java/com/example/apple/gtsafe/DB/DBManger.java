package com.example.apple.gtsafe.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.apple.gtsafe.Bean.logviewBean;
import com.example.apple.gtsafe.contactBean.contactattrBean;
import com.example.apple.gtsafe.contactBean.contactcateBean;
import com.example.apple.gtsafe.contactBean.contactconBean;
import com.example.apple.gtsafe.domain.Chart;
import com.example.apple.gtsafe.domain.LogContact;
import com.example.apple.gtsafe.domain.LogTpl;
import com.example.apple.gtsafe.domain.LogTplattr;
import com.example.apple.gtsafe.domain.LogTplcate;
import com.example.apple.gtsafe.domain.Loglist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by siokagami on 15/4/26.
 */
public class DBManger
{
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private contactcateBean contactconbean;

    public DBManger(Context context)
    {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //添加填报日志列表
    public void addtologview(String addTime,String sturts)
    {
        db.beginTransaction();
        try
        {
            db.execSQL("INSERT INTO gt_logview VALUES(null,?,?)",new Object[]
                    {addTime,sturts});
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {

        }
        finally {
            db.endTransaction();

        }
    }
    //添加填报日志信息
    public void addtologinfo(String contactid ,String attrid,String logid,String type)
    {
        db.beginTransaction();
        try
        {
            db.execSQL("INSERT INTO gt_loginfo VALUES(null,?,?,?,?)",new Object[]
                    {contactid,attrid,type,logid});
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
        }
        finally
        {
            db.endTransaction();
        }
    }
    //添加图表数据
    public void addchart(List<Chart.TargetPoint> targetPointList){
        db.beginTransaction();
        try
        {
            for (Chart.TargetPoint targetPoint : targetPointList)
            {
                db.execSQL("INSERT INTO gt_chart VALUES(null,?,?)",new Object[]
                        {targetPoint.getPoint(),targetPoint.getDay()});
            };
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {

        }
        finally
        {
            db.endTransaction();
        }
    }
    public void add(List<Loglist.ServerModel> logviewBeanList)
    {
        db.beginTransaction();
        try
        {
            for (Loglist.ServerModel logviewbean : logviewBeanList)
            {
                db.execSQL("INSERT INTO gt_logview VALUES(null,?,?,?,?)",new Object[]
                        {logviewbean.getId(),logviewbean.getAddTime(),logviewbean.getStatusName(),logviewbean.getScore()+logviewbean.getScore2()+logviewbean.getScore3()});
            };
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {

        }
        finally
        {
            db.endTransaction();
        }
    }
//    public void addlogtpldatabase(List<LogTpl> logTplList)
//    {
//        db.beginTransaction();
//        try
//        {
//            for(LogTpl logTpl: logTplList)
//            {
//                db.execSQL("INSERT INTO gt_contact VALUES(null,?,?,?)",new Object[]{logTpl.getId(),logTpl.getName()});
//                if(logTpl.getChildren()!=null) {
//                    for (LogTplcate logTplcate : logTpl.getChildren()) {
//                        db.execSQL("INSERT INTO gt_attr VALUES(null,?,?,?,?)", new Object[]{logTplcate.getId(), logTplcate.getName(), logTpl.getId()});
//                        if (logTplcate.getChildren()!=null){
//
//
//                        }
//                    }
//                }
//            };
//            db.setTransactionSuccessful();
//        }
//        catch (Exception e)
//        {
//            //System.out.println("mdkdjfnjkdsnjksd vkjsdf sdfsdfhdskjfhjdsk"+e);
//
//        }
//        finally
//        {
//            db.endTransaction();
//        }
//    }
    public void addlogtpldatabase(List<LogTpl> logTplList){
        db.beginTransaction();
        try
        {
            for(LogTpl logTpl: logTplList)
            {
                db.execSQL("INSERT INTO gt_logtpl VALUES(null,?,?)",new Object[]{logTpl.getId(),logTpl.getName()});
                if(logTpl.getSubContactCateList()!=null) {
                    for (LogTplcate logTplcate : logTpl.getSubContactCateList()) {
                        db.execSQL("INSERT INTO gt_logtplcate VALUES(null,?,?,?)", new Object[]{logTplcate.getId(), logTplcate.getName(), logTpl.getId()});
                        if (logTplcate.getContactList()!=null){
                            for(LogContact logContact : logTplcate.getContactList()) {
                                db.execSQL("INSERT INTO gt_logcontact VALUES(null,?,?,?,?)", new Object[]{logContact.getId(), logContact.getName(), logTplcate.getId(),logContact.getType()});
                                if (logContact.getAttributeList()!=null){
                                    for(LogTplattr logTplattr : logContact.getAttributeList()){
                                        db.execSQL("INSERT INTO gt_logattr VALUES(null,?,?,?,?)", new Object[]{logTplattr.getId(), logTplattr.getName(), logContact.getId(),logTplattr.getCheck()});

                                    }
                                }

                            }

                        }
                    }
                }
            }
//            for(int i=0;i<logTplList.size();i++){
//                db.execSQL("INSERT INTO gt_logtpl VALUES(null,?,?)",new Object[]{logTplList.get(i).getId(),logTplList.get(i).getName()});
//                if(logTplList.get(i).getSubContactCateList()!=null){
//                    for(int j=0;j<logTplList.get(i).getSubContactCateList().size();j++){
//                        db.execSQL("INSERT INTO gt_logtplcate VALUES(null,?,?,?)", new Object[]{logTplList.get(i).getSubContactCateList().get(j).getId(), logTplList.get(i).getSubContactCateList().get(j).getName(), logTplList.get(i).getId()});
//                    }
//                }
//            }
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            System.out.println("mdkdjfnjkdsnjksd vkjsdf sdfsdfhdskjfhjdsk"+e);

        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }
    public void addcontacttodatabase(List<contactconBean> contactconBeanList)
    {
        db.beginTransaction();
        try
        {
            for(contactconBean contactconbean: contactconBeanList)
            {
//                db.execSQL("INSERT INTO gt_contact VALUES(null,?,?,?)",new Object[]{contactconbean.getId(),contactconbean.getName(),contactconbean.getType()});
//                if(contactconbean.getAttr()!=null) {
//                    for (contactattrBean contactattrbean : contactconbean.getAttr()) {
//                        db.execSQL("INSERT INTO gt_attr VALUES(null,?,?,?,?)", new Object[]{contactattrbean.getId(), contactattrbean.getName(), contactconbean.getId(),contactattrbean.getScore()});
//                        //System.out.println("234567654345676543456gggg" + contactattrbean.getId() + "????" + contactattrbean.getName() + "&&&&&&" + contactconbean.getId());
//                    }
//                }
            };
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            //System.out.println("mdkdjfnjkdsnjksd vkjsdf sdfsdfhdskjfhjdsk"+e);

        }
        finally
        {
            db.endTransaction();
        }
    }

    public void addhistorytodatabase(List<contactconBean> contactconBeanList)
    {
        db.beginTransaction();
        try
        {
            for(contactconBean contactconbean: contactconBeanList)
            {
                db.execSQL("INSERT INTO gt_yesterday VALUES(null,?,?)",new Object[]{contactconbean.getId(),contactconbean.getVal()});
            };
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {

        }
        finally {
            db.endTransaction();
        }
    }
    public void delete()
    {
        db.beginTransaction();
        try
        {
            db.execSQL("DELETE FROM gt_logview");
            db.execSQL("DELETE FROM gt_logcontact");
            db.execSQL("DELETE FROM gt_logattr");
            db.execSQL("DELETE FROM gt_chart");
            db.execSQL("DELETE FROM gt_logtpl");
            db.execSQL("DELETE FROM gt_logtplcate");
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {

        }
        finally
        {
            db.endTransaction();
        }
    }
    public  void delete_yesterday()
    {
        db.beginTransaction();
        try
        {
            db.execSQL("DELETE FROM gt_yesterday");
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {

        }
        finally {
            db.endTransaction();
        }
    }
    //查询图表状态
    public List<Map<String,String>> querrychart(){
        List<Map<String,String>> datalist = new ArrayList<Map<String,String>>();
        try {
            Cursor cursor=getquerrychart();
            while (cursor.moveToNext()){
                Map<String,String> map = new HashMap<String,String>();

                map.put("point",cursor.getString(cursor.getColumnIndex("point")));
                map.put("day",cursor.getString(cursor.getColumnIndex("day")));
                datalist.add(map);
            }
        }
        catch (Exception e)
        {

        }
        finally {
            dbclose();
        }
        return datalist;
    }
    //查询日志状态
    public List<Map<String,Object>> querrylog()
    {
        List <Map<String,Object>> datalist = new ArrayList<Map<String, Object>>();
        try
        {
            Cursor cursor = getquerrylog();
            while (cursor.moveToNext())
            {
                Map<String,Object> map =new HashMap<String,Object>();
                map.put("id",cursor.getString(cursor.getColumnIndex("id")));
                map.put("score",cursor.getString(cursor.getColumnIndex("score")));
                map.put("addTime",cursor.getString(cursor.getColumnIndex("addTime")));
                map.put("status",cursor.getString(cursor.getColumnIndex("status")));
                datalist.add(map);
            }
        }
        catch (Exception e)
        {
        }
        finally
        {

        }
        return datalist;
    }
    //查询一级分类
    public List<Map<String,Object>> querrylogtpl()
    {
        List <Map<String,Object>> datalist = new ArrayList<Map<String, Object>>();
        try
        {
            Cursor cursor = getquerrylogtpl();
            while (cursor.moveToNext())
            {
                Map<String,Object> map =new HashMap<String,Object>();
                map.put("name",cursor.getString(cursor.getColumnIndex("name")));
                map.put("id",cursor.getString(cursor.getColumnIndex("id")));
                datalist.add(map);
            }
        }
        catch (Exception e)
        {
        }
        finally
        {

        }
        return datalist;
    }
    //查询二级分类
    public List<Map<String,Object>> querrycate()
    {
        List <Map<String,Object>> datalist = new ArrayList<Map<String, Object>>();
        try
        {
            Cursor cursor = getquerrycate();
            while (cursor.moveToNext())
            {
                System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                Map<String,Object> map =new HashMap<String,Object>();
                map.put("name",cursor.getString(cursor.getColumnIndex("name")));
                map.put("id",cursor.getString(cursor.getColumnIndex("id")));
                map.put("father",cursor.getString(cursor.getColumnIndex("father")));
                datalist.add(map);
            }
        }
        catch (Exception e)
        {
        }
        finally
        {

        }
        return datalist;
    }
    //查询触点值
    public List<Map<String,Object>> querrycontact()
    {
        List <Map<String,Object>> datalist = new ArrayList<Map<String, Object>>();
        try
        {
            Cursor cursor = getquerrycon();
            while (cursor.moveToNext())
            {
                Map<String,Object> map =new HashMap<String,Object>();
                map.put("name",cursor.getString(cursor.getColumnIndex("name")));
                map.put("id",cursor.getString(cursor.getColumnIndex("id")));
                map.put("father",cursor.getString(cursor.getColumnIndex("father")));
                map.put("type",cursor.getString(cursor.getColumnIndex("type")));
                datalist.add(map);
            }
        }
        catch (Exception e)
        {
        }
        finally
        {

        }
        return datalist;
    }
    //查询触点属性值
    public List<Map<String,Object>> querryattr()
    {
        List <Map<String,Object>> datalist = new ArrayList<Map<String, Object>>();
        try
        {
            Cursor cursor = getquerrattr();
            while (cursor.moveToNext())
            {
                Map<String,Object> map =new HashMap<String,Object>();
                map.put("name",cursor.getString(cursor.getColumnIndex("name")));
                map.put("father",cursor.getString(cursor.getColumnIndex("father")));
                map.put("checked",cursor.getString(cursor.getColumnIndex("checked")));
                map.put("id",cursor.getString(cursor.getColumnIndex("id")));
                datalist.add(map);
            }
        }
        catch (Exception e)
        {
        }
        finally
        {

        }
        return datalist;
    }
    //查询日志
    public List<Map<String,Object>> querryloginfo()
    {
        List <Map<String ,Object>> datalist = new ArrayList<Map<String, Object>>();
        try
        {
            Cursor cursor = getquerryloginfo();
            while(cursor.moveToNext())
            {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("contact",cursor.getString(cursor.getColumnIndex("contact")));
                map.put("attr",cursor.getString(cursor.getColumnIndex("attr")));
                map.put("logid",cursor.getString(cursor.getColumnIndex("logid")));
                map.put("type",cursor.getString(cursor.getColumnIndex("type")));
                datalist.add(map);
            }
        }
        catch (Exception e)
        {

        }
        finally {

        }
        return datalist;
    }
    public Cursor getquerrylog()
    {
        Cursor cursor = db.rawQuery("SELECT * FROM gt_logview",null);
        return cursor;
    }
    public Cursor getquerrychart(){
        Cursor cursor = db.rawQuery("SELECT * FROM gt_chart",null);
        return cursor;
    }
    public Cursor getquerrycon()
    {
        Cursor cursor = db.rawQuery("SELECT * FROM gt_logcontact",null);
        return cursor;
    }
    public Cursor getquerrattr()
    {
        Cursor cursor = db.rawQuery("SELECT * FROM gt_logattr",null);
        return cursor;
    }
    public Cursor getquerrylogtpl(){
        Cursor cursor = db.rawQuery("SELECT * FROM gt_logtpl",null);
        return cursor;
    }
    public Cursor getquerrycate(){
        Cursor cursor = db.rawQuery("SELECT * FROM gt_logtplcate",null);
        return cursor;
    }
    public Cursor getquerryloginfo()
    {
        Cursor cursor = db.rawQuery("SELECT * FROM gt_loginfo",null);
        return cursor;
    }
    public void dbclose()
    {
        db.close();
    }
}

