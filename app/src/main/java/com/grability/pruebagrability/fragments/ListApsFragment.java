package com.grability.pruebagrability.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import com.grability.ventanas.Ventanas;
import com.grability.pruebagrability.R;
import com.grability.pruebagrability.adapters.DataAdapterGridView;
import com.grability.pruebagrability.adapters.DataAdapterRecyclerView;
import com.grability.pruebagrability.constants.ConstantesGenerales;
import com.grability.pruebagrability.impl.GetListApps;
import com.grability.pruebagrability.modelo.FeedDTO;
import com.grability.pruebagrability.utilidades.DetectDevice;


public class ListApsFragment extends Fragment {

    private DataAdapterRecyclerView dataAdapterRecyclerView;
    private DataAdapterGridView dataAdapterGridView;
    private GridView gridView;
    private Boolean isTablet;
    private static RecyclerView rv;
    private Context context;
    private OnFragmentInteractionListener mListener;


    public ListApsFragment() {

    }


    public static ListApsFragment newInstance(int page, String title) {
        ListApsFragment fragment = new ListApsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // page = getArguments().getInt("someInt", 0);
           // title = getArguments().getString("someTitle");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_apps, container, false);
        context= view.getContext();

        isTablet= DetectDevice.isTabletDevice(context);

        if (isTablet)
        {
            gridView = (GridView) view.findViewById(R.id.grid);
        }
        else
        {
            rv = (RecyclerView) view.findViewById(R.id.rv);
            LinearLayoutManager llm = new LinearLayoutManager(context);
            rv.setLayoutManager(llm);
            rv.setHasFixedSize(true);
        }

        new getListApps().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return view;

    }

    private void screenSmartphone(){





    }






    /**
     * Tarea asincrona para obtener los datos
     */
    private class getListApps extends AsyncTask<String, String, Object> {

        private Ventanas ventanas;

        public   getListApps( )
        {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(ConstantesGenerales.TAG, "Inicia tarea obtener lista de apps");
            //Se muestra una alerta cargando
            ventanas= new Ventanas();
            ventanas.loadingIndeterminate(context, getString(R.string.tituloCargando), getString(R.string.mensajeBuscando, ""));
        }

        @Override
        protected FeedDTO doInBackground(String... arg0) {
            //Se carga los datos a obtener
            GetListApps getListApps = new GetListApps();
            FeedDTO feed;

            //Se envia la peticion
            feed=getListApps.obtenerDatos();

            return feed;
        }

        @Override
        protected void onPostExecute(Object resultado) {
            //Detenemos la ventana de espera
            ventanas.stopLoading();
            try {
                FeedDTO feed = (FeedDTO) resultado;
                if (feed.getFeed().getEntry().size() > 0)
                    for (int i = 0; i < feed.getFeed().getEntry().size(); i++) {
                        Log.d(ConstantesGenerales.TAG, "dato[" + i + "] " + feed.getFeed().getEntry().get(i).toString());
                    }
                else {
                    ventanas.alert(context, "", getString(R.string.mensajeAppsNoEncontrada));
                }

                //cargamos los datos en el dataAdapterGridView grilla
                if(isTablet) {
                    dataAdapterGridView = new DataAdapterGridView(getActivity(), feed.getFeed().getEntry());
                    gridView.setAdapter(dataAdapterGridView);
                }
                else{
                    dataAdapterRecyclerView = new DataAdapterRecyclerView(getActivity(), feed.getFeed().getEntry());
                    rv.setAdapter(dataAdapterRecyclerView);

                }
            }
            catch (Exception ex){}
        }

    }
























    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
