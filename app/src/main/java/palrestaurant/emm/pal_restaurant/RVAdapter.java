package palrestaurant.emm.pal_restaurant;

import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView platillo;
        TextView precio;
        TextView descripcion;
        ImageView foto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            platillo = (TextView)itemView.findViewById(R.id.nombre_platillo);
            precio = (TextView)itemView.findViewById(R.id.precio_platillo);
            descripcion = (TextView)itemView.findViewById(R.id.desc_platillo);
            foto = (ImageView)itemView.findViewById(R.id.platillo_photo);
        }

    }

    List<Platillo> platillos;

    RVAdapter(List<Platillo> platillos){
        this.platillos = platillos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.platillo.setText(platillos.get(i).getNombre());
        personViewHolder.precio.setText(platillos.get(i).getPrecio());
        personViewHolder.descripcion.setText(platillos.get(i).getDescripcion());
        personViewHolder.foto.setImageResource(platillos.get(i).getPhotoId());
    }

    @Override
    public int getItemCount() {
        return platillos.size();
    }

}