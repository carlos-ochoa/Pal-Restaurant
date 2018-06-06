package palrestaurant.emm.pal_restaurant;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import java.util.List;

public class AdapterResena extends RecyclerView.Adapter<AdapterResena.ResenaViewHolder> {
    private String[] mDataset;
    private Context mCtx;
    private List<resena> resenaList;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterResena(Context mCtx, List<resena> resenaList) {
        this.mCtx = mCtx;
        this.resenaList = resenaList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ResenaViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_resena, null);
        return new ResenaViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ResenaViewHolder holder, int position) {
        resena res= resenaList.get(position);
        //Cargamos la imagen
        holder.nombre_usuario.setText(res.getNombre());
        holder.descripcion.setText(res.getDescripcion());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return resenaList.size();
    }

    class ResenaViewHolder extends RecyclerView.ViewHolder{
        TextView nombre_usuario,descripcion;
        ImageView foto;
        public ResenaViewHolder(final View itemView){
            super(itemView);
            //Enlace a los widgets
            nombre_usuario = itemView.findViewById(R.id.textView10);
            descripcion = itemView.findViewById(R.id.desc_platillo);
            foto = itemView.findViewById(R.id.platillo_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(),perfilRestauranteComensal.class);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
