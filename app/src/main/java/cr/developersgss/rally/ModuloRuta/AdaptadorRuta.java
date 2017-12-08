package cr.developersgss.rally.ModuloRuta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cr.developersgss.rally.ModuloJueces.AdaptadorJuez;
import cr.developersgss.rally.Objetos.TablaJuez;
import cr.developersgss.rally.Objetos.TablaRuta;
import cr.developersgss.rally.R;


/**
 * Created by Krlos on 07/12/2017.
 */

public class AdaptadorRuta extends RecyclerView.Adapter<AdaptadorRuta.ViewHolder>
        implements View.OnClickListener{
    private List<TablaRuta> listarutas;
    private Context context;
    private  View.OnClickListener listener;


    public AdaptadorRuta(List<TablaRuta> listarutas, Context context) {
        this.listarutas = listarutas;
        this.context = context;
    }

    @Override
    public AdaptadorRuta.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View   v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_rutas,parent,false);

        v.setOnClickListener(this);
        return  new AdaptadorRuta.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorRuta.ViewHolder holder, int position) {
        TablaRuta lista = listarutas.get(position);

        holder.IDRuta.setText(lista.getIDRuta());
        holder.IDRally.setText(lista.getIDRally());
        holder.NombreRuta.setText(lista.getNombreRuta());
        holder.FechaInicioRuta.setText(lista.getFechaInicioRuta());
        holder.HoraInicioRuta.setText(lista.getHoraInicioRuta());
        holder.EstadoRuta.setText(lista.getEstadoRuta());
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public int getItemCount() {
        return listarutas.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView IDRally,IDRuta,NombreRuta,FechaInicioRuta,HoraInicioRuta,EstadoRuta;

        public ViewHolder(View itemView) {
            super(itemView);

            IDRuta= itemView.findViewById(R.id.LIDRuta);
            IDRally= itemView.findViewById(R.id.LIDRallyr);
            NombreRuta= itemView.findViewById(R.id.LNombreRuta);
            FechaInicioRuta= itemView.findViewById(R.id.LFecha);
            HoraInicioRuta= itemView.findViewById(R.id.LHora);
            EstadoRuta= itemView.findViewById(R.id.LEstado);
        }
    }
}
