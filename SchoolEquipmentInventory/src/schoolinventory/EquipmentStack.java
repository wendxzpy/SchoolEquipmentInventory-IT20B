package schoolinventory;

public class EquipmentStack {

    private Equipment[] stack;
    private int top;
    private int capacity;

    public EquipmentStack(int capacity) {
        this.capacity = capacity;
        stack = new Equipment[capacity];
        top = -1;
    }
     public boolean push(Equipment equipment) {

        if (isFull()) {
            return false;
        }

        stack[++top] = equipment;
        return true;
    }
       public Equipment pop() {

        if (isEmpty()) {
            return null;
        }

        Equipment removed = stack[top];
        stack[top] = null;
        top--;

        return removed;
    }