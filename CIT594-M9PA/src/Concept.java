
/**
 * =========================================================
 * WARNING: DO NOT MODIFY THIS CLASS
 *
 * This file is provided as part of the starter code.
 * It is used by the autograder and your implementation
 * will rely on its behavior being consistent.
 * =========================================================
 */
public class Concept {
    public String label;
    public String type;

    public Concept(String label, String type) {
        this.label = label.toLowerCase();
        this.type = type.toLowerCase();
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Concept(label=%s, type=%s)", label, type);
    }
}