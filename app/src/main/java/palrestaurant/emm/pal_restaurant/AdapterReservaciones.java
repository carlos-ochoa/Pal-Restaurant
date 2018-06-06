package palrestaurant.emm.pal_restaurant;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import java.util.List;

public class AdapterReservaciones extends RecyclerView.Adapter<AdapterReservaciones.ReservacionViewHolder> {
    private String[] mDataset;
    private Context mCtx;
    private List<Reservacion> ReservacionesList;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterReservaciones(Context mCtx, List<Reservacion> ReservacionesList) {
        this.mCtx = mCtx;
        this.ReservacionesList = ReservacionesList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReservacionViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_reservar_lugar, null);
        return new ReservacionViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ReservacionViewHolder holder, int position) {
        Reservacion reservacion = ReservacionesList.get(position);

        holder.hora.setText(reservacion.getHora());
        holder.fecha.setText(reservacion.getFecha());
        holder.mesa.setText(reservacion.getMesa());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ReservacionesList.size();
    }

    class ReservacionViewHolder extends RecyclerView.ViewHolder{
        TextView hora,fecha,mesa;
        Button aceptar, cancelar;
        public ReservacionViewHolder(View itemView){
            super(itemView);
            //Enlace a los widgets
            hora = itemView.findViewById(R.id.TVHora);
            fecha = itemView.findViewById(R.id.TVFecha);
            mesa = itemView.findViewById(R.id.TVMesa);
            aceptar = itemView.findViewById(R.id.btnAceptar);

        }
    }
}
