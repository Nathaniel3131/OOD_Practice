package com.company;

import com.company.characters.Warrior;
import com.company.characters.HasWarriorBehind;

import java.util.*;
import java.util.function.Supplier;
import java.util.LinkedList;
import java.util.Queue;



public class Army {
    //    Queue<Warrior> troops = new LinkedList<Warrior>();
//
//       public void addUnits(Warrior warrior){
//            troops.add(warrior);
//    }
//
//    Warrior peekFirst(){
//           return troops.peek();
//    }
//
//    void removeFirst(){
//           troops.poll();
//    }
    private List<Warrior> troops = new LinkedList<Warrior>();

    static class UnitInArmy extends Warrior implements HasWarriorBehind{
        Warrior warrior;
        Warrior behind;

        public UnitInArmy(Warrior warrior)
        {
            this.warrior = warrior;
        }

        @Override
        public Warrior getWarriorBehind() {
            return behind;
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

        @Override
        public void hit(Warrior opponent) {
            warrior.hit(opponent);
        }
    }

    public Army addUnits(Warrior warrior)
    {
        if(!troops.isEmpty())
        {
            troops.get(troops.size()-1).setWarriorBehind(warrior);
        }
        troops.add(warrior);
        //troops.offer(warrior);
        return this;
    }

    public Army addUnits(Supplier<Warrior> factory, int quantity)
    {
        for(int i=0; i<quantity; i++)
        {
            addUnits(factory.get());
        }
        return this;
    }

    public Iterator<Warrior> firstAlive()
    {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<Warrior>
    {
        @Override
        public boolean hasNext() {
            while(peekFirst() != null && !peekFirst().isAlive())
            {
                removeFirst();
            }
            return peekFirst() != null;
        }

        @Override
        public Warrior next() {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }
            return peekFirst();
        }
    }

    private Warrior peekFirst()
    {
        return troops.isEmpty() ? null: troops.get(0);
    }

    // delete
    private void removeFirst()
    {
        troops.remove(0);
    }
}
