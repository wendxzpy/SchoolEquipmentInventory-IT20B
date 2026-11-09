
package schoolinventory;

public class EquipmentStack {

    private Equipment[] stack;
    private int top;
    private int capacity;

    // CONSTRUCTOR
    public EquipmentStack(int capacity) {
        this.capacity = capacity;
        stack = new Equipment[capacity];
        top = -1;
    }

    // PUSH - ADD EQUIPMENT
    public boolean push(Equipment equipment) {

        if (isFull()) {
            return false;
        }

        stack[++top] = equipment;
        return true;
    }

    // POP - REMOVE TOP EQUIPMENT
    public Equipment pop() {

        if (isEmpty()) {
            return null;
        }

        Equipment removed = stack[top];
        stack[top] = null;
        top--;

        return removed;
    }

    // PEEK - VIEW TOP EQUIPMENT
    public Equipment peek() {

        if (isEmpty()) {
            return null;
        }

        return stack[top];
    }

    // CHECK IF STACK IS EMPTY
    public boolean isEmpty() {
        return top == -1;
    }

    // CHECK IF STACK IS FULL
    public boolean isFull() {
        return top == capacity - 1;
    }

    // GET STACK SIZE
    public int size() {
        return top + 1;
    }

    // SEARCH EQUIPMENT BY ID
    public Equipment search(String equipmentID) {

        for (int i = top; i >= 0; i--) {

            if (stack[i].getEquipmentID()
                    .equalsIgnoreCase(equipmentID)) {

                return stack[i];
            }
        }

        return null;
    }

    // UPDATE EQUIPMENT AVAILABILITY
    public boolean updateAvailability(
            String equipmentID, String newAvailability) {

        Equipment equipment = search(equipmentID);

        if (equipment == null) {
            return false;
        }

        equipment.setAvailability(newAvailability);
        return true;
    }

    // GET EQUIPMENT BY INDEX
    public Equipment getEquipment(int index) {

        if (index < 0 || index > top) {
            return null;
        }

        return stack[index];
    }
}
