import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Property<E> implements Cloneable{
    private Double totalEffectOnProperty = new Double(0);               // az che noii e??????
    private String name;
    private String fieldOfEffecting;
    private int currentGrade;
    private int numberOfUpgrades;
    private boolean isDependOnEffectedSoldier;
    private boolean isDependOnUserHero;
    private boolean isPermanently;

    private double[] constantProperty;
    private double[] attackPowerCoefficient;
    private double[] maximumHealthCoefficient;
    private double[] maximumMagicCoefficient;
    private double[] healthCoefficient;
    private double[] magicCoefficient;
    private double[] healthRefillRateCoefficient;
    private double[] magicRefillRateCoefficient;

    private Map<E, Double> valueOfEffectingOnEffectedSoldiers = new HashMap<>();

    //-------------------------------------------------------------- Constructors

    public Property(PropertyHandler propertyHandler) {
        setName(propertyHandler.getName());
        setNumberOfUpgrades(propertyHandler.getNumberOfUpgrades());
        setCurrentGrade(0);
        setDependOnEffectedSoldier(propertyHandler.isDependOnEffectedSoldier());
        setDependOnUserHero(propertyHandler.isDependOnUserHero());
        setPermanently(propertyHandler.isPermanently());
        setConstantProperty(propertyHandler.getConstantProperty());
        setAttackPowerCoefficient(propertyHandler.getAttackPowerCoefficient());
        setMaximumHealthCoefficient(propertyHandler.getMaximumHealthCoefficient());
        setMaximumMagicCoefficient(propertyHandler.getMaximumMagicCoefficient());
        setHealthCoefficient(propertyHandler.getHealthCoefficient());
        setMagicCoefficient(propertyHandler.getMagicCoefficient());
        setHealthRefillRateCoefficient(propertyHandler.getHealthRefillRateCoefficient());
        setMagicRefillRateCoefficient(propertyHandler.getMagicRefillRateCoefficient());
    }

    public String getName() {
        return this.name;
    }

    //---------------------------------------    Functions

    protected Property<E> clone() throws CloneNotSupportedException {
        Property<E> property = (Property<E>) super.clone();
        property.setValueOfEffectingOnEffectedSoldiers(new HashMap<>());
        return property;
    }

    private <T> void calculateProperty(T relatedSoldier) {
        this.totalEffectOnProperty = this.constantProperty[this.currentGrade - 1];
        if (relatedSoldier instanceof Hero) {
            this.totalEffectOnProperty += this.attackPowerCoefficient[this.currentGrade - 1] * ((Hero) relatedSoldier).getAttackPower();
            this.totalEffectOnProperty += this.maximumHealthCoefficient[this.currentGrade - 1] * ((Hero) relatedSoldier).getMaximumHealth();
            this.totalEffectOnProperty += this.maximumMagicCoefficient[this.currentGrade - 1] * ((Hero) relatedSoldier).getMaximumMagic();
            this.totalEffectOnProperty += this.healthCoefficient[this.currentGrade - 1] * ((Hero) relatedSoldier).getCurrentHealth();
            this.totalEffectOnProperty += this.magicCoefficient[this.currentGrade - 1] * ((Hero) relatedSoldier).getCurrentMagic();
            this.totalEffectOnProperty += this.healthRefillRateCoefficient[this.currentGrade - 1] * ((Hero) relatedSoldier).getHealthRefillRate();
            this.totalEffectOnProperty += this.magicRefillRateCoefficient[this.currentGrade - 1] * ((Hero) relatedSoldier).getMagicRefillRate();
            return;
        }
        if (relatedSoldier instanceof Enemy) {
            this.totalEffectOnProperty += this.attackPowerCoefficient[this.currentGrade - 1] * ((Enemy) relatedSoldier).getAttackPower();
            this.totalEffectOnProperty += this.maximumHealthCoefficient[this.currentGrade - 1] * ((Enemy) relatedSoldier).getMaximumHealth();
            this.totalEffectOnProperty += this.healthCoefficient[this.currentGrade - 1] * ((Enemy) relatedSoldier).getCurrentHealth();
            return;
        }
    }

    public <T> void effect(T relatedSoldier, Hero owner, Hero userHero) {
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
            classOfSoldier = classOfSoldier.getSuperclass();
            try {
                field = classOfSoldier.getDeclaredField(this.name);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                cond = 1;
            }
        }
        String type = field.getGenericType().toString();
        Class classOfSoldier2 = relatedSoldier.getClass();
        Field field2 = null;
        if (this.name.equals("currentHealth")) {
            cond = 0;
            try {
                field2 = classOfSoldier2.getDeclaredField("maximumHealth");
            } catch (NoSuchFieldException e) {
                cond = 1;
            }
            if (cond == 1) {
                cond = 0;
                classOfSoldier2 = classOfSoldier2.getSuperclass();
                try {
                    field2 = classOfSoldier2.getDeclaredField("maximumHealth");
                } catch (NoSuchFieldException e) {
                    cond = 1;
                }
            }
            if (cond == 1) {
                cond = 0;
                classOfSoldier2 = classOfSoldier2.getSuperclass().getSuperclass();
                try {
                    field2 = classOfSoldier2.getDeclaredField("maximumHealth");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    cond = 1;
                }
            }
        }
        if (this.name.equals("currentMagic")) {
            cond = 0;
            try {
                field2 = classOfSoldier2.getDeclaredField("maximumMagic");
            } catch (NoSuchFieldException e) {
                cond = 1;
            }
            if (cond == 1) {
                cond = 0;
                classOfSoldier2 = classOfSoldier2.getSuperclass();
                try {
                    field2 = classOfSoldier2.getDeclaredField("maximumMagic");
                } catch (NoSuchFieldException e) {
                    cond = 1;
                }
            }
            if (cond == 1) {
                cond = 0;
                classOfSoldier2 = classOfSoldier2.getSuperclass().getSuperclass();
                try {
                    field2 = classOfSoldier2.getDeclaredField("maximumMagic");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    cond = 1;
                }
            }
        }
        try {
            if (this.isDependOnEffectedSoldier)
                this.calculateProperty(relatedSoldier);
            else if (this.isDependOnUserHero){
                this.calculateProperty(userHero);
            }
            else {
                this.calculateProperty(owner);
            }
            if (type.equals("int")) {
                if (this.name.equals("currentHealth")) {
                    if (((Integer) field.get(relatedSoldier) + this.totalEffectOnProperty.intValue()) > ((Integer)field2.get(relatedSoldier)))
                        totalEffectOnProperty = (Double) field2.get(relatedSoldier) - (Double) field.get(relatedSoldier);
                }
                if (this.name.equals("currentMagic")) {
                    if (((Integer) field.get(relatedSoldier) + this.totalEffectOnProperty.intValue()) > ((Integer)field2.get(relatedSoldier)))
                        totalEffectOnProperty = (Double) field2.get(relatedSoldier) - (Double) field.get(relatedSoldier);
                }
                field.set(relatedSoldier, (Integer) field.get(relatedSoldier) + this.totalEffectOnProperty.intValue());
            }
            if (type.equals("double")) {
                if (this.name.equals("currentHealth")) {
                    if (((Double) field.get(relatedSoldier) + this.totalEffectOnProperty) > ((Double) field2.get(relatedSoldier)))
                        totalEffectOnProperty = (Double) field2.get(relatedSoldier) - (Double) field.get(relatedSoldier);
                }
                if (this.name.equals("currentMagic")) {
                    if (((Double) field.get(relatedSoldier) + this.totalEffectOnProperty) > ((Double) field2.get(relatedSoldier)))
                        totalEffectOnProperty = (Double) field2.get(relatedSoldier) - (Double) field.get(relatedSoldier);
                }
                field.set(relatedSoldier, (Double) field.get(relatedSoldier) + this.totalEffectOnProperty);
            }
            if (this.valueOfEffectingOnEffectedSoldiers.containsKey(relatedSoldier)) {
                this.valueOfEffectingOnEffectedSoldiers.put((E) relatedSoldier, this.valueOfEffectingOnEffectedSoldiers.get(relatedSoldier) + this.totalEffectOnProperty);
            }
            else {
                this.valueOfEffectingOnEffectedSoldiers.put((E) relatedSoldier, this.totalEffectOnProperty);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> void removeEffect(T relatedSoldier) {
        if (isPermanently)
            return;
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
        String type = field.getGenericType().toString();
        try {
            if (type.equals("int")) {
                field.set(relatedSoldier, (Integer) field.get(relatedSoldier) - this.valueOfEffectingOnEffectedSoldiers.get(relatedSoldier).intValue());
            }
            if (type.equals("double")) {
                field.set(relatedSoldier, (Double) field.get(relatedSoldier) - this.valueOfEffectingOnEffectedSoldiers.get(relatedSoldier));
            }
            if (type.equals("float")) {
                field.set(relatedSoldier, (Float) field.get(relatedSoldier) - this.valueOfEffectingOnEffectedSoldiers.get(relatedSoldier).floatValue());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.valueOfEffectingOnEffectedSoldiers.remove(relatedSoldier);
    }

    public String showCurrentUpgradeNumber(){
        if(this.numberOfUpgrades == 0){
            return "not acquired";
        }
        else{
            String sentence = "Upgrade Number : " + this.currentGrade;
            return sentence;
        }
    }

    //--------------------------------------------------------------      Getter & Setter
    public int getNumberOfUpgrades() {
        return numberOfUpgrades;
    }

    public void setNumberOfUpgrades(int numberOfUpgrades) {
        this.numberOfUpgrades = numberOfUpgrades;
    }

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

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }

    public void setTotalEffectOnProperty(Double totalEffectOnProperty) {
        this.totalEffectOnProperty = totalEffectOnProperty;
    }

    public String getFieldOfEffecting() {
        return fieldOfEffecting;
    }

    public void setFieldOfEffecting(String fieldOfEffecting) {
        this.fieldOfEffecting = fieldOfEffecting;
    }

    public boolean isDependOnEffectedSoldier() {
        return isDependOnEffectedSoldier;
    }

    public void setDependOnEffectedSoldier(boolean dependOnEffectedSoldier) {
        isDependOnEffectedSoldier = dependOnEffectedSoldier;
    }

    public Map<E, Double> getValueOfEffectingOnEffectedSoldiers() {
        return valueOfEffectingOnEffectedSoldiers;
    }

    public void setValueOfEffectingOnEffectedSoldiers(Map<E, Double> valueOfEffectingOnEffectedSoldiers) {
        this.valueOfEffectingOnEffectedSoldiers = valueOfEffectingOnEffectedSoldiers;
    }

    public boolean isDependOnUserHero() {
        return isDependOnUserHero;
    }

    public void setDependOnUserHero(boolean dependOnUserHero) {
        isDependOnUserHero = dependOnUserHero;
    }

    public boolean isPermanently() {
        return isPermanently;
    }

    public void setPermanently(boolean permanently) {
        isPermanently = permanently;
    }
}