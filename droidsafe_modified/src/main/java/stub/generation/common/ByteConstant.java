package stub.generation.common;

import soot.Type;
import soot.jimple.ArithmeticConstant;
import soot.jimple.NumericConstant;
import soot.util.Switch;

public class ByteConstant extends ArithmeticConstant {

    private final byte value;


    protected ByteConstant(byte b){
        this.value = b;
    }

    public static ByteConstant v(byte value) {
        return new ByteConstant(value);
    }

    @Override
    public ArithmeticConstant and(ArithmeticConstant arithmeticConstant) {
        return null;
    }

    @Override
    public ArithmeticConstant or(ArithmeticConstant arithmeticConstant) {
        return null;
    }

    @Override
    public ArithmeticConstant xor(ArithmeticConstant arithmeticConstant) {
        return null;
    }

    @Override
    public ArithmeticConstant shiftLeft(ArithmeticConstant arithmeticConstant) {
        return null;
    }

    @Override
    public ArithmeticConstant shiftRight(ArithmeticConstant arithmeticConstant) {
        return null;
    }

    @Override
    public ArithmeticConstant unsignedShiftRight(ArithmeticConstant arithmeticConstant) {
        return null;
    }

    @Override
    public NumericConstant add(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant subtract(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant multiply(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant divide(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant remainder(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant equalEqual(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant notEqual(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant lessThan(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant lessThanOrEqual(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant greaterThan(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant greaterThanOrEqual(NumericConstant numericConstant) {
        return null;
    }

    @Override
    public NumericConstant negate() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public void apply(Switch aSwitch) {

    }
}
