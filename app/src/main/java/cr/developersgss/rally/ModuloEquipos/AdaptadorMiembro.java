package cr.developersgss.rally.ModuloEquipos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cr.developersgss.rally.Objetos.TablaEquipos;
import cr.developersgss.rally.Objetos.TablaPersonaEquipo;
import cr.developersgss.rally.R;

/**
 * Created by Krlos on 11/12/2017.
 */

public class AdaptadorMiembro extends RecyclerView.Adapter<AdaptadorMiembro.ViewHolder>
        implements View.OnClickListener{

    private List<TablaPersonaEquipo> listamiembros;
    private Context context;
    private  View.OnClickListener listener;


    public AdaptadorMiembro(List<TablaPersonaEquipo> listamiembros, Context context) {
        this.listamiembros = listamiembros;
        this.context = context;
    }

    @Override
    public AdaptadorMiembro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View   v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_miembros,parent,false);


        v.setOnClickListener(this);
        return  new AdaptadorMiembro.ViewHolder(v);

    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(AdaptadorMiembro.ViewHolder holder, int position) {

        TablaPersonaEquipo lista = listamiembros.get(position);

        holder.IDPersonaEquipo.setText(lista.getIDPersonaEquipo());
        holder.IDEquipo.setText(lista.getIDEquipo());
        holder.IDRally.setText(lista.getIDRally());
        holder.Nombre.setText(lista.getNombrePersonaEquipo());
        if (lista.getLider().equals("1")){
            //holder.lider.setText(lista.getLider());
            holder.lider.setText("Sí");
        }else {
            holder.lider.setText("No");
        }

    }

    @Override
    public int getItemCount() {
        return listamiembros.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView  IDPersonaEquipo,IDRally,IDEquipo,Nombre,lider;

        public ViewHolder(View itemView) {
            super(itemView);

            IDEquipo= itemView.findViewById(R.id.LIDEquipoM);
            IDRally= itemView.findViewById(R.id.LIDRallyM);
            Nombre= itemView.findViewById(R.id.LNombreM);
            IDPersonaEquipo= itemView.findViewById(R.id.LIDMiembro);
            lider= itemView.findViewById(R.id.LLider);

        }
    }
}
