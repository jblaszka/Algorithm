import java.util.ArrayList;

public class Algorithm {
    private int changes = 6;

    public ArrayList<Integer> numberConversion(ArrayList<Integer> list){
        ArrayList<Integer> newList = convertToDivisible(list);
        if(changes == 6){
            newList = useLeftoverChanges(newList);
            newList = useLeftoverChanges(newList);
        }else if(changes > 2 & changes < 6){
            newList = useLeftoverChanges(newList);
        }
        return newList;
    }

    public ArrayList<Integer> convertToDivisible(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (int number : list) {
            int sum = sumOfDigitsInNumber(number);
            int length = String.valueOf(number).length();
            ArrayList<Integer> numberAsArray = convertIntToArray(number, length);

            if (sum % 3 == 1) {
                int counter_ones = 0;
                changes = changes - 2;
                for (int j = 0; j < length; j++) {
                    int digit = numberAsArray.get(j);
                    if (digit < 8){
                        if (counter_ones == 0){
                            digit = digit + 2;
                        }else {
                            digit = digit + 1;
                        }
                        numberAsArray.set(j, digit);
                        break;
                    }else if (digit == 8){
                        counter_ones = counter_ones + 1;
                        digit = digit + 1;
                        if(counter_ones == 2){
                            break;
                        }
                    }
                }
            }else if(sum % 3 == 2){
                changes = changes - 1;
                for(int i = 0; i < length; i++){
                    int digit = numberAsArray.get(i);
                    if(digit < 9){
                        digit = digit + 1;
                        numberAsArray.set(i, digit);
                        break;
                    }
                }
            }
            newList.add(convertArrayToInt(numberAsArray));
        }
        return newList;
    }

    public ArrayList<Integer> useLeftoverChanges(ArrayList<Integer> list) {
    int temp_x = list.get(0);
    int temp_y = list.get(1);
    int temp_z = list.get(2);

    int length_x = String.valueOf(temp_x).length();
    int length_y = String.valueOf(temp_y).length();
    int length_z = String.valueOf(temp_z).length();

    ArrayList<Integer> array_x = convertIntToArray(list.get(0), length_x);
    ArrayList<Integer> array_y = convertIntToArray(list.get(1), length_y);
    ArrayList<Integer> array_z = convertIntToArray(list.get(2), length_z);

    ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
    arrayLists.add(array_x);
    arrayLists.add(array_y);
    arrayLists.add(array_z);

    int sum_x = temp_y + temp_z;
    int sum_y = temp_x + temp_z;
    int sum_z = temp_x + temp_y;

    for (ArrayList<Integer> arrayList : arrayLists){
        int counter = 3;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) < 7) {
                arrayList.set(i, arrayList.get(i) + counter);
                break;
            } else if (arrayList.get(i) == 7) {
                int temp = Integer.min(counter, 2);
                counter -= temp;
                arrayList.set(i, arrayList.get(i) + temp);
                if (counter == 0) {
                    break;
                }
            } else if (arrayList.get(i) == 8) {
                int temp = Integer.min(counter, 1);
                counter -= temp;
                arrayList.set(i, arrayList.get(i) + temp);
                if (counter == 0) {
                    break;
                }
            }
        }
    }

    temp_x = convertArrayToInt(array_x);
    temp_y = convertArrayToInt(array_y);
    temp_z = convertArrayToInt(array_z);

    sum_x = sum_x + temp_x;
    sum_y = sum_y + temp_y;
    sum_z = sum_z + temp_z;

    if(sum_x >= sum_y & sum_x >= sum_z){
        list.set(0, temp_x);
    }else if(sum_y >= sum_x & sum_y >= sum_z){
        list.set(1, temp_y);
    }else {
        list.set(2, temp_z);
    }

    return list;
    }

    public int sumOfDigitsInNumber(int number){
        return String.valueOf(number)
                .chars()
                .map(Character::getNumericValue)
                .sum();
    }

    public ArrayList<Integer> convertIntToArray(int number, int length){
        ArrayList<Integer> numberInArray = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int numberToAdd = String.valueOf(number).charAt(i) - '0';
            numberInArray.add(numberToAdd);
        }
        return numberInArray;
    }

    public int convertArrayToInt(ArrayList<Integer> arr){
        StringBuilder numberInString = new StringBuilder();

        for (Integer integer : arr) {
            numberInString.append(integer);
        }
        return Integer.parseInt(numberInString.toString());
    }
}
