package com.bStone.util.collection;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class WeakList<WeakType> implements List<WeakType>
{
	private List<WeakReference<WeakType>> list;

	public WeakList()
	{
		this.list = new ArrayList<WeakReference<WeakType>>();
	}

	@Override
	public boolean add(WeakType e)
	{
		return this.list.add(new WeakReference<WeakType>(e));
	}

	@Override
	public void add(int index, WeakType element)
	{
		this.list.add(index, new WeakReference<WeakType>(element));
	}

	@Override
	public boolean addAll(Collection<? extends WeakType> c)
	{
		ArrayList<WeakReference<WeakType>> arraylist = new ArrayList<WeakReference<WeakType>>();

		for(WeakType obj : c)
		{
			arraylist.add(new WeakReference<WeakType>(obj));
		}

		return this.list.addAll(arraylist);
	}

	@Override
	public boolean addAll(int index, Collection<? extends WeakType> c)
	{
		ArrayList<WeakReference<WeakType>> arraylist = new ArrayList<WeakReference<WeakType>>();

		for(WeakType obj : c)
		{
			arraylist.add(new WeakReference<WeakType>(obj));
		}

		return this.list.addAll(index, arraylist);
	}

	@Override
	public void clear()
	{
		this.list.clear();
	}

	@Override
	public boolean contains(Object o)
	{
		for(WeakReference<WeakType> ref : this.list)
		{
			if (ref != null && ref.get() == o)
			{
				return true;
			}
		}

		return false;
	}

	public boolean contains(WeakReference<WeakType> ref)
	{
		return this.list.contains(ref);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		for(Object obj : c)
		{
			if (!this.contains(obj))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public WeakType get(int index)
	{
		return this.list.get(index).get();
	}

	@Override
	public int indexOf(Object o)
	{
		for(int i = 0; i < this.list.size(); i++)
		{
			if (this.list.get(i).get() == o)
			{
				return i;
			}
		}

		return -1;
	}

	@Override
	public boolean isEmpty()
	{
		return this.list.isEmpty();
	}

	@Override
	public Iterator<WeakType> iterator()
	{
		ArrayList<WeakType> arraylist = new ArrayList<WeakType>();

		for(WeakReference<WeakType> ref : this.list)
		{
			if (ref.get() != null)
			{
				arraylist.add(ref.get());
			}
		}

		return arraylist.iterator();
	}

	@Override
	public int lastIndexOf(Object o)
	{
		for(int i = this.list.size() - 1; i >= 0; i--)
		{
			if (this.list.get(i).get() == o)
			{
				return i;
			}
		}

		return -1;
	}

	@Override
	public ListIterator<WeakType> listIterator()
	{
		return null;
	}

	@Override
	public ListIterator<WeakType> listIterator(int index)
	{
		return null;
	}

	@Override
	public boolean remove(Object o)
	{
		return this.remove(this.indexOf(o)) != null;
	}

	public boolean remove(WeakReference<WeakType> ref)
	{
		return this.list.remove(ref);
	}

	@Override
	public WeakType remove(int index)
	{
		return this.removeReference(index).get();
	}

	public WeakReference<WeakType> removeReference(int index)
	{
		return this.list.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		boolean flag = true;
		for(Object obj : c)
		{
			flag = flag && this.remove(obj);
		}

		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		for(int i = this.list.size() - 1; i >= 0; i--)
		{
			if (!c.contains(this.list.get(i).get()))
			{
				this.list.remove(i);
			}
		}

		return true;
	}

	@Override
	public WeakType set(int index, WeakType element)
	{
		return this.list.set(index, new WeakReference<WeakType>(element)).get();
	}

	@Override
	public int size()
	{
		return this.list.size();
	}

	@Override
	public List<WeakType> subList(int fromIndex, int toIndex)
	{
		ArrayList<WeakType> arraylist = new ArrayList<WeakType>();
		for(int i = fromIndex; i < toIndex; i++)
		{
			arraylist.add(this.list.get(i).get());
		}

		return arraylist;
	}

	@Override
	public Object[] toArray()
	{
		Object[] array = new Object[this.list.size()];
		for(int i = 0; i < this.list.size(); i++)
		{
			array[i] = this.list.get(i);
		}

		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a)
	{
		for(int i = 0; i < a.length && i < this.list.size(); i++)
		{
			a[i] = (T) this.list.get(i);
		}

		return a;
	}
}
