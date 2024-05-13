package breakthehabit.model;

public class GastoEmCigarros extends Usuario{


    public static double calcularGasto(){

      double valorGasto;
      Usuario.atribuicaoValoresCalculados();

      valorGasto = (Usuario.getQtdDiasVicio() * Usuario.getReaisGastoDiaUser());

      return valorGasto;
    }

    public static double calcularEconomia(){
        double valorEconomizado;
        Usuario.atribuicaoValoresCalculados();

        valorEconomizado = (Usuario.getQtdDiasSemFumar() * Usuario.getReaisGastoDiaUser());

        return valorEconomizado;
    }


}
