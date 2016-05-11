import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Property {
    private double totalEffectOnProperty;
    private String name;
    private String fieldOfEffecting;
    private int currentGrade;
    private boolean isDependOnEffectedSoldier;

    private double[] constantProperty;
    private double[] attackPowerCoefficient;
    private double[] maximumHealthCoefficient;
    private double[] maximumMagicCoefficient;
    private double[] healthCoefficient;
    private double[] magicCoefficient;
    private double[] healthRefillRateCoefficient;
    private double[] magicRefillRateCoefficient;

    //--------------------------------------------------------------    Constructor

    public Property(PropertyHandler propertyHandler){
        setName(propertyHandler.getName());
        setConstantProperty(propertyHandler.getConstantProperty());
        setAttackPowerCoefficient(propertyHandler.getAttackPowerCoefficient());
        setMaximumHealthCoefficient(propertyHandler.getMaximumHealthCoefficient());
        setMaximumMagicCoefficient(propertyHandler.getMaximumMagicCoefficient());
        setHealthCoefficient(propertyHandler.getHealthCoefficient());
        setMagicCoefficient(propertyHandler.getMagicCoefficient());
        setHealthRefillRateCoefficient(propertyHandler.getHealthRefillRateCoefficient());
        setMagicRefillRateCoefficient(propertyHandler.getMagicRefillRateCoefficient());
    }

    public String getName(){
        return this.name;
    }

    //--------------------------------------------------------------      Getter & Setter

    public double[] getConstantProperty() {
        return constantProperty;
    }

    public void setConstantProperty(double[] constantProperty) {
        this.constantProperty = constantProperty;
    }

    public double getTotalEffectOnProperty() {
        return totalEffectOnProperty;
    }

    public void setTotalEffectOnProperty(double totalEffectOnProperty) {
        this.totalEffectOnProperty = totalEffectOnProperty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getAttackPowerCoefficient() {
        return attackPowerCoefficient;
    }

    public void setAttackPowerCoefficient(double[] attackPowerCoefficient) {
        this.attackPowerCoefficient = attackPowerCoefficient;
    }

    public double[] getMaximumHealthCoefficient() {
        return maximumHealthCoefficient;
    }

    public void setMaximumHealthCoefficient(double[] maximumHealthCoefficient) {
        this.maximumHealthCoefficient = maximumHealthCoefficient;
    }

    public double[] getMaximumMagicCoefficient() {
        return maximumMagicCoefficient;
    }

    public void setMaximumMagicCoefficient(double[] maximumMagicCoefficient) {
        this.maximumMagicCoefficient = maximumMagicCoefficient;
    }

    public double[] getHealthCoefficient() {
        return healthCoefficient;
    }

    public void setHealthCoefficient(double[] healthCoefficient) {
        this.healthCoefficient = healthCoefficient;
    }

    public double[] getMagicCoefficient() {
        return magicCoefficient;
    }

    public void setMagicCoefficient(double[] magicCoefficient) {
        this.magicCoefficient = magicCoefficient;
    }

    public double[] getHealthRefillRateCoefficient() {
        return healthRefillRateCoefficient;
    }

    public void setHealthRefillRateCoefficient(double[] healthRefillRateCoefficient) {
        this.healthRefillRateCoefficient = healthRefillRateCoefficient;
    }

    public double[] getMagicRefillRateCoefficient() {
        return magicRefillRateCoefficient;
    }

    public void setMagicRefillRateCoefficient(double[] magicRefillRateCoefficient) {
        this.magicRefillRateCoefficient = magicRefillRateCoefficient;
    }

    //---------------------------------------    Functions

    private <T> void calculateProperty(T relatedSoldier) {
        this.totalEffectOnProperty += this.constantProperty[this.currentGrade];
        if (relatedSoldier instanceof Hero) {
            this.totalEffectOnProperty += this.attackPowerCoefficient[this.currentGrade] * ((Hero)relatedSoldier).getAttackPower();
            this.totalEffectOnProperty += this.maximumHealthCoefficient[this.currentGrade] * ((Hero) relatedSoldier).getMaximumHealth();
            this.totalEffectOnProperty += this.maximumMagicCoefficient[this.currentGrade] * ((Hero) relatedSoldier).getMaximumMagic();
            this.totalEffectOnProperty += this.healthCoefficient[this.currentGrade] * ((Hero) relatedSoldier).getCurrentHealth();
            this.totalEffectOnProperty += this.magicCoefficient[this.currentGrade] * ((Hero) relatedSoldier).getCurrentMagic();
            this.totalEffectOnProperty += this.healthRefillRateCoefficient[this.currentGrade] * ((Hero) relatedSoldier).getHealthRefillRate();
            this.totalEffectOnProperty += this.magicRefillRateCoefficient[this.currentGrade] * ((Hero) relatedSoldier).getMagicRefillRate();
            return;
        }
        if (relatedSoldier instanceof Enemy) {
            this.totalEffectOnProperty += this.attackPowerCoefficient[this.currentGrade] * ((Enemy)relatedSoldier).getAttackPower();
            this.totalEffectOnProperty += this.maximumHealthCoefficient[this.currentGrade] * ((Enemy) relatedSoldier).getMaximumHealth();
            this.totalEffectOnProperty += this.healthCoefficient[this.currentGrade] * ((Enemy) relatedSoldier).getCurrentHealth();
            return;
        }
    }

    public <T> void effect(T relatedSoldier, Hero owner) {
        Class classOfSoldier = relatedSoldier.getClass();
        Field field = null;
        int cond = 0;
        try {
            field = classOfSoldier.getDeclaredField(this.name);
        } catch (NoSuchFieldException e) {
            cond = 1;
        }
        if (cond == 1) {
            cond = 0;
            classOfSoldier = classOfSoldier.getSuperclass();
            try {
                field = classOfSoldier.getDeclaredField(this.name);
            } catch (NoSuchFieldException e) {
                cond = 1;
            }
        }
        if (cond == 1) {
            cond = 0;
            classOfSoldier = classOfSoldier.getSuperclass().getSuperclass();
            try {
                field = classOfSoldier.getDeclaredField(this.name);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                cond = 1;
            }
        }
        String type = field.getGenericType().toString();
        try {
            if (this.isDependOnEffectedSoldier)
                this.calculateProperty(relatedSoldier);
            else {
                this.calculateProperty(owner);
            }
            if (type.equals("int")) {
                field.set(relatedSoldier, (int)field.get(relatedSoldier) + (int)this.totalEffectOnProperty);
            }
            if (type.equals("double")) {
                field.set(relatedSoldier, (double)field.get(relatedSoldier) + this.totalEffectOnProperty);
            }
            if (type.equals("float")) {
                field.set(relatedSoldier, (float)field.get(relatedSoldier) + (float)this.totalEffectOnProperty);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> void removeEffect(T relatedSoldiers) {
        //TODO
    }

}
