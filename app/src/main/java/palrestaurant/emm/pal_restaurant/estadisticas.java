package palrestaurant.emm.pal_restaurant;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class estadisticas extends AppCompatActivity {
    private PieChart pieChart;
    private BarChart barChart;
    private String[] preguntas = new String[]{"Pregunta 1","Pregunta 2","Pregunta 3","Pregunta 4","Pregunta 5","Pregunta 6","Pregunta 7","Pregunta 8","Pregunta 9","Pregunta 10"};
    private int[] sale = new int[]{25,25,10,12,54,23,43,12,23,19};
    private int[] color = new int[]{Color.BLUE,Color.RED,Color.GRAY,Color.GREEN, Color.YELLOW,Color.CYAN, Color.MAGENTA,Color.BLUE,Color.RED,Color.GRAY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        pieChart = (PieChart) findViewById(R.id.pie);
        barChart = (BarChart) findViewById(R.id.bar);
        createCharts();
    }

    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY){
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);

        return chart;
    }

    private void legend(Chart chart){
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for(int i = 0; i < preguntas.length; i++){
            LegendEntry entry = new LegendEntry();
            entry.formColor = color[i];
            entry.label = preguntas[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    private ArrayList<BarEntry> getBarEntries(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < sale.length; i++){
            entries.add(new BarEntry(i,sale[i]));
        }
        return  entries;
    }
    private ArrayList<PieEntry> getPieEntries(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < sale.length; i++){
            entries.add(new PieEntry(sale[i]));
        }
        return  entries;
    }
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(preguntas));
    }
    private void axisLeft(YAxis axis){
        axis.setSpaceTop(60);
        axis.setAxisMinimum(0);
    }
    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }

    public void createCharts(){
        barChart = (BarChart) getSameChart(barChart,"Calificaciones", Color.RED, Color.WHITE,3000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());

        pieChart = (PieChart) getSameChart(pieChart,"Porcentajes de respuesta", Color.GRAY, Color.WHITE, 3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //pieChart.setDrawHoleEnabled(false);
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(color);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData(){
        BarDataSet barDataSet = (BarDataSet)getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData(){
        PieDataSet pieDataSet = (PieDataSet)getData(new PieDataSet(getPieEntries(),""));
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}
