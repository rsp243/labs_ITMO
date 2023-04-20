package src.classes;

import src.enums.Material;

public class Clothes {
    private String name;
    private Material material;

    public Clothes(String nameOfClothing, Material materialOfClothing) {
        this.name = nameOfClothing;
        this.material = materialOfClothing;
    }

    public Material getMaterial() {
        return this.material;
    }

    public String getName() {
        return name;
    }

    
    @Override
    public String toString() {
        return "Clothes [name=" + name + ", material=" + material + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((material == null) ? 0 : material.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Clothes other = (Clothes) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (material != other.material)
            return false;
        return true;
    }
}
