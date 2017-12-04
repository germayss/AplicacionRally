package cr.developersgss.rally.ModuloJueces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cr.developersgss.rally.Objetos.IDPunto;
import cr.developersgss.rally.Objetos.IDRally;
import cr.developersgss.rally.Objetos.TablaJuez;
import cr.developersgss.rally.R;

/**
 * Created by Krlos on 03/12/2017.
 */

public class AdaptadorJuez extends RecyclerView.Adapter<AdaptadorJuez.ViewHolder> {

    private List<TablaJuez> listajueces;
    private Context context;



    public AdaptadorJuez(List<TablaJuez> listajueces, Context context) {
        this.listajueces = listajueces;
        this.context = context;
    }

    @Override
    public AdaptadorJuez.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View   v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_jueces,parent,false);

        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorJuez.ViewHolder holder, int position) {
        TablaJuez lista = listajueces.get(position);

        holder.IDRally.setText(lista.getIDRally());
        holder.IDJuez.setText(lista.getIDJuez());
        holder.IDpunto.setText(lista.getPuntocontrol());
        holder.Nombre.setText(lista.getNombre());
        holder.Usuario.setText(lista.getUsuario());
        holder.Contraseña.setText(lista.getContrasena());
        holder.Tipo.setText(lista.getTipo());
    }

    @Override
    public int getItemCount() {
        return listajueces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       public  TextView IDRally,IDJuez,IDpunto,Nombre,Usuario,Contraseña,Tipo;

        public ViewHolder(View itemView) {
            super(itemView);

            IDRally= itemView.findViewById(R.id.LIDRally);
            IDJuez= itemView.findViewById(R.id.LIDJuez);
            IDpunto= itemView.findViewById(R.id.LIDPunto);
            Nombre= itemView.findViewById(R.id.LIDNombre);
            Usuario= itemView.findViewById(R.id.LUsuario);
            Contraseña= itemView.findViewById(R.id.LContrasena);
            Tipo= itemView.findViewById(R.id.LTipo);

        }
    }
}
