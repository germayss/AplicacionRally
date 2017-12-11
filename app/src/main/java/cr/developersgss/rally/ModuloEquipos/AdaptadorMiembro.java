package cr.developersgss.rally.ModuloEquipos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Krlos on 11/12/2017.
 */

public class AdaptadorMiembro   extends RecyclerView.Adapter<AdaptadorEquipo.ViewHolder>
        implements View.OnClickListener{

    private List<TablaPersonaEquipo> listamiembross;
    private Context context;
    private  View.OnClickListener listener;

    public AdaptadorMiembro(List<TablaPersonaEquipo> listamiembross, Context context) {
        this.listamiembross = listamiembross;
        this.context = context;
    }


    @Override
    public AdaptadorEquipo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AdaptadorEquipo.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listamiembross.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}
