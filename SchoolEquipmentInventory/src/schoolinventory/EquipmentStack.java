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
        public Equipment peek() {

        if (isEmpty()) {
            return null;
        }

        return stack[top];
    }
          public boolean isEmpty() {
        return top == -1;
    }
           public boolean isFull() {
        return top == capacity - 1;
    }
           public int size() {
        return top + 1;
    }
           public Equipment search(String equipmentID) {

        for (int i = top; i >= 0; i--) {

            if (stack[i].getEquipmentID()
                    .equalsIgnoreCase(equipmentID)) {

                return stack[i];
            }
        }

        return null;
    }
            public boolean updateAvailability(
            String equipmentID, String newAvailability) {

        Equipment equipment = search(equipmentID);

        if (equipment == null) {
            return false;
        }

        equipment.setAvailability(newAvailability);
        return true;
        
        public Equipment getEquipment(int index) {

        if (index < 0 || index > top) {
            return null;
        }

        return stack[index];
    }
}