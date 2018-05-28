package palrestaurant.emm.pal_restaurant;

import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PlatilloViewHolder> {
    private String[] mDataset;
    private Context mCtx;
    private List<Platillo> platilloList;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter(Context mCtx, List<Platillo> platilloList) {
        this.mCtx = mCtx;
        this.platilloList = platilloList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PlatilloViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
       LayoutInflater inflater = LayoutInflater.from(mCtx);
       View view = inflater.inflate(R.layout.card_platillo, null);
       return new PlatilloViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PlatilloViewHolder holder, int position) {
        Platillo platillo = platilloList.get(position);
        //Cargamos la imagen
        holder.nombre.setText(platillo.getNombre());
        holder.precio.setText(platillo.getPrecio());
        holder.descripcion.setText(platillo.getDescripcion());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return platilloList.size();
    }

    class PlatilloViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,precio,descripcion;
        ImageView foto;
        public PlatilloViewHolder(View itemView){
            super(itemView);
            //Enlace a los widgets
            nombre = itemView.findViewById(R.id.nombre_platillo);
            precio = itemView.findViewById(R.id.precio_platillo);
            descripcion = itemView.findViewById(R.id.desc_platillo);
            foto = itemView.findViewById(R.id.platillo_photo);
        }
    }
}
