package com.gaby.micasaamigable;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by Carlos Moya on 20/02/2018.
 */

public class Restful  extends AsyncTask<Void,Void,String[][]> {

    private String HTTP_RESTFUL;
    private String url_p="https://casaamigable.000webhostapp.com/";
    private Context context;
    private String info,nombre,pass,name,lastn;

    public Restful(Context context, String info,String nombre,String pass, String name, String lastn) {
        this.context = context;
        this.info=info;
        this.nombre=nombre;
        this.pass=pass;
        this.name=name;
        this.lastn=lastn;
        HTTP_RESTFUL=getURL(info);
        Log.d("url","url:"+HTTP_RESTFUL);
    }
    public Restful(Context context, String info,String nombre,String pass) {
        this.context = context;
        this.info=info;
        this.nombre=nombre;
        this.pass=pass;
        HTTP_RESTFUL=getURL(info);
        Log.d("url","url:"+HTTP_RESTFUL);
    }

    public Restful(Context context, String info) {
        this.context = context;
        this.info=info;
        HTTP_RESTFUL=getURL(info);
        Log.d("url","url:"+HTTP_RESTFUL);
    }

    /**
     * Metodo que se conecta al RESTFUL para obtener un resultado
     * */
    public String[][] getRestFul()
    {
        String jsonResult="";
        String[][] list3=new String[1][5];
        String[][] listE=new String[1][1];
        listE[0][0] = "0";
        try {
            URL url = new URL(HTTP_RESTFUL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            jsonResult = inputStreamToString(inputStream).toString();//response.getEntity().getContent()).toString();
            ////////////////////////////////////

            Log.d("restful","json: "+jsonResult);

            int pos=jsonResult.indexOf('{');
            int pos2=jsonResult.lastIndexOf('}');
            if(pos==-1) { list3[0][0]="0"; return list3;}
            String jsnres=jsonResult.substring(pos, pos2 + 1);

            Log.d("restful","jsonFinal: "+jsonResult);

            JSONObject object = new JSONObject(jsnres);
            //obtiene el status
            String status = object.getString("status");
            System.out.print("status:"+status);
            if( status.equals("50") )           //50 -> todo esta bien
            {
                //extrae los registros
                JSONArray array = new JSONArray(object.getString("Registros"));
                list3=new String[array.length()+1][5];
                for (int i = 1; i <= array.length(); i++)
                {
                    //recorre cada registro y concatena el resultado
                    JSONObject row = array.getJSONObject(i-1);
                    switch(this.info)
                    {
                        case "registro":
                            list3[0][0]=status;
                            list3[0][1]=row.getString("respuesta");
                            //list3[i][0]=row.getString("registro");
                            break;
                        case "login":
                            list3[0][0]=status;
                            list3[0][1]=row.getString("respuesta");
                            //list3[i][0]=row.getString("login");
                            break;
                        case "GetData":
                            list3[0][0]=status;
                            list3[0][1]=row.getString("respuesta");
                           // list3[0][2]=list3[0][1]["user"];
                            break;
                    }
                }
                return list3;
            }
            if( status.equals("500") )list3[0][0]="500";
            else   list3[0][0]="0";
            return list3;
        }catch (UnknownHostException e)
        {
            listE[0][0] = e.getMessage();
        }
        catch (ClientProtocolException e) {
            listE[0][0] = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            listE[0][0] = e.getMessage();
            e.printStackTrace();
        } catch (JSONException e) {
            listE[0][0] = e.getMessage();
            e.printStackTrace();
        }

        return listE;
    }

    /**
     * Transforma el InputStream en un String
     * @return StringBuilder
     * */
    private StringBuilder inputStreamToString(InputStream is)
    {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader rd = new BufferedReader( new InputStreamReader(is) );
        try
        {
            while( (line = rd.readLine()) != null )
                stringBuilder.append(line);
        }
        catch( IOException e)
        {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    @Override
    protected  String[][] doInBackground(Void... arg0){
        String[][] resul = getRestFul();
        return resul;
    }

    @Override
    protected void onPostExecute(String resul[][]) {
        if(resul[0].length>0) {

            Log.d("res", "res:" + resul[0][0].toString());
            if (resul[0][0].equals("50")) {
                switch (this.info) {
                    case "registro":
                        ((MainActivity)context).registroOk();
                        break;
                    case "login":
                        if(resul[0][1].toString().equals("ok"))
                           ((MainActivity)context).datosOk();
                        else ((MainActivity)context).mensaje("verifica tus datos");
                        break;


                }
            } else {
                if (resul[0][0].equals("500")) {
                    switch (this.info) {
                        case "registro":
                            //  ((Main) context).mensaje("Error en el registro, verifique sus datos");
                            break;
                    }
                } //else ((Main) context).mensaje("Ocurrio un error \n Verifique su conexión");
            }
        }
    }

    public String getURL(String info)
    {
        switch(info)
        {
            case "registro": return url_p+"control.php?action=registro&usr="+nombre+"&pass="+pass+"&name="+name+"&lastn"+lastn;
            case "login": return url_p+"control.php?action=login&usr="+nombre+"&pass="+pass;
            case "GetData": return url_p+"control.php?action="+info;

            default: return url_p+"control_p.php?action=error";
        }
    }
}