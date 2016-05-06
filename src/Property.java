/**
 * Created by asus-pc on 5/5/2016.
 */
public class Property {
    private double[] totalEffectOnProperty;
    private String name;

    private double[] constantProperty;
    private double[] attackPowerCoefficient;
    private double[] maximumHealthCoefficient;
    private double[] maximumMagicCoefficient;
    private double[] healthCoefficient;
    private double[] magicCoefficient;
    private double[] healthRefillRateCoefficient;
    private double[] magicRefillRateCoefficient;
    //--------------------------------------------------------------
    public Property(){

    }
    public String getName(){
        return this.name;
    }
    //--------------------------------------------------------------

    private void calculateProperty() {
        //TODO
    }

    public void effect(String heroName) {
        Hero hero = Hero.listOfHeroes.get(heroName);
        if(getName().equals("attackPower")){

        }
    }

    public void removeEffect() {
        //TODO
    }

}
