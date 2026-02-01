package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeGasto {

    private List<Gasto> gastos;

    public GerenciadorDeGasto(){
        this.gastos = new ArrayList<>();
    }
    public void adicionarGasto(Gasto gasto){
        gastos.add(gasto);
    }
    public double totalDoDia(LocalDate data) {
        double total = 0.0;
        for (Gasto g : gastos) {
            if (g.getData().equals(data)) {
                total += g.getValor();
            }
        }
        return total;
    }
    public double totalDoMes(int mes, int ano) {
        double total = 0.0;
        for (Gasto g : gastos) {
            if (g.getData().getMonthValue() == mes &&
                    g.getData().getYear() == ano) {
                total += g.getValor();
            }
        }
        return total;
    }
    public List<Gasto> getGastos() {
        return gastos;
    }
}
