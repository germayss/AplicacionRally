package cr.developersgss.rally.ModuloEquipos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import cr.developersgss.rally.Objetos.TablaEquipos;
import cr.developersgss.rally.R;

/**
 * Created by Krlos on 10/12/2017.
 */

public class AdaptadorEquipo extends RecyclerView.Adapter<AdaptadorEquipo.ViewHolder>
        implements View.OnClickListener {

    private List<TablaEquipos> listaequipos;
    private Context context;
    private  View.OnClickListener listener;

    public AdaptadorEquipo(List<TablaEquipos> listaequipos, Context context) {
        this.listaequipos = listaequipos;
        this.context = context;
    }

    @Override
    public AdaptadorEquipo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View   v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_equipos,parent,false);


        v.setOnClickListener(this);
        return  new AdaptadorEquipo.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdaptadorEquipo.ViewHolder holder, int position) {
        TablaEquipos lista = listaequipos.get(position);

        holder.IDEquipo.setText(lista.getIDEquipo());
        holder.IDRally.setText(lista.getIDRally());
        holder.Nombre.setText(lista.getNombreEquipo());
        holder.Usuario.setText(lista.getUsuarioquipo());
        holder.Contraseña.setText(lista.getPasswordEquipo());
    }

    @Override
    public int getItemCount() {
        return listaequipos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView IDRally,IDEquipo,Nombre,Usuario,Contraseña;

        public ViewHolder(View itemView) {
            super(itemView);

            IDEquipo= itemView.findViewById(R.id.LIDEquipo);
            IDRally= itemView.findViewById(R.id.LIDRallyE);
            Nombre= itemView.findViewById(R.id.LIDNombreE);
            Usuario= itemView.findViewById(R.id.LUsuarioE);
            Contraseña= itemView.findViewById(R.id.LContrasenaE);
        }
    }
}
