package bmicalculator.base.engine;

import java.text.DecimalFormat;

public class BMICalculator{


    public static Float kgCmBMICal(float weight, float height){
            float heightm = height/100;
            float heightm2 = heightm * heightm;
            float BMI = weight/heightm2;
      
            return BMI;

    }

    public static Float kgFtBMICal(float weight, int feet, int inch ){
        int feetConvert = feet * 12;
        float height = feetConvert + inch;
        height = height * 2.54f;
        float heightm = height/100;
        float heightm2 = heightm * heightm;
        float BMI = weight/heightm2;


        return BMI;

    }

    public static Float ibFtBMICal(float weight, int feet, int inch ){
        int feetConvert = feet * 12;
        float height = feetConvert + inch;
        height = height * 2.54f;
        float heightm = height/100;
        float heightm2 = heightm * heightm;
        weight = weight  * 0.454f;
        float BMI = weight/heightm2;


        return BMI;

    }

    public static Float ibCmBMICal(float weight, float height){
        
        float heightm = height/100;
        float heightm2 = heightm * heightm;
        weight = weight  * 0.454f;
        float BMI = weight/heightm2;

        return BMI;

    }



}
