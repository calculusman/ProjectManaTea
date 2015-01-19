package com.bStone.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ObjectList<LivingObject>
{
	protected final ArrayList<LivingObject> initList;
	protected final ArrayList<LivingObject> livingList;
	protected final ArrayList<LivingObject> trashList;

	public ObjectList()
	{
		this.initList = new ArrayList<LivingObject>();
		this.livingList = new ArrayList<LivingObject>();
		this.trashList = new ArrayList<LivingObject>();
	}

	public void terminate()
	{
		this.initList.clear();
		this.livingList.clear();
		this.trashList.clear();
	}

	/**Should run each tick to clean and update list*/
	public List<LivingObject> update()
	{
		this.dumpTrashFromList();
		return this.addSpawnToLivingList();
	}

	/**Add instances to main list*/
	public List<LivingObject> addSpawnToLivingList()
	{
		ArrayList<LivingObject> livinglist = new ArrayList<LivingObject>();

		for(LivingObject obj : this.initList)
		{
			if (!this.existsTrash(obj))
			{
				this.livingList.add(obj);
				livinglist.add(obj);
			}
		}

		this.initList.clear();

		return livinglist;
	}

	/**Dump removed instances from memory*/
	public void dumpTrashFromList()
	{
		for(LivingObject obj : this.trashList)
		{
			if (!this.livingList.remove(obj))
			{
				this.initList.remove(obj);
			}
		}

		this.trashList.clear();
	}

	/**Add instance to initiate quota*/
	public LivingObject add(LivingObject parLiving)
	{
		if (parLiving != null)
		{
			this.initList.add(parLiving);
		}

		return parLiving;
	}

	/**Add list of instances to initiate quota*/
	public LivingObject[] add(LivingObject[] parLiving)
	{
		for(LivingObject obj : parLiving)
		{
			if (obj != null)
			{
				this.initList.add(obj);
			}
		}

		return parLiving;
	}

	/**Add list of instances to initiate quota*/
	public Collection<LivingObject> add(Collection<LivingObject> parLivings)
	{
		for(LivingObject obj : parLivings)
		{
			if (obj != null)
			{
				this.initList.add(obj);
			}
		}

		return parLivings;
	}

	/**Add instance to dump quota; returns true if instance exists*/
	public boolean remove(LivingObject parLiving)
	{
		if (parLiving != null)
		{
			if (this.existsLiving(parLiving))
			{
				this.trashList.add(parLiving);
				return true;
			}
		}

		return false;
	}

	/**Add list of instances to dump quota; returns true if all instances exists*/
	public boolean remove(LivingObject[] parLiving)
	{
		boolean flag = true;
		for(LivingObject obj : parLiving)
		{
			if (!this.remove(obj))
			{
				flag = false;
			}
		}

		return flag;
	}

	/**Add list of instances to dump quota; returns true if all instances exists*/
	public boolean remove(Collection<LivingObject> parLivings)
	{
		boolean flag = true;
		for(LivingObject obj : parLivings)
		{
			if (!this.remove(obj))
			{
				flag = false;
			}
		}

		return flag;
	}

	/**Get list of all instances of type par1Class*/
	public Object[] get(Class<LivingObject> parClass)
	{
		ArrayList<LivingObject> objects = new ArrayList<LivingObject>();
		for(LivingObject obj : this.livingList)
		{
			if (obj.getClass().isAssignableFrom(parClass))
			{
				objects.add(obj);
			}
		}

		return objects.toArray();
	}

	/**Returns true if instance exists in main list*/
	public boolean exists(LivingObject parLiving)
	{
		for(LivingObject obj : this.livingList)
		{
			if (obj.equals(parLiving))
			{
				return true;
			}
		}

		return false;
	}

	/**Returns true if instance exists in spawn list*/
	public boolean existsSpawn(LivingObject parLiving)
	{
		for(LivingObject obj : this.initList)
		{
			if (obj.equals(parLiving))
			{
				return true;
			}
		}

		return false;
	}

	/**Returns true if instance exists in trash list*/
	public boolean existsTrash(LivingObject parLiving)
	{
		for(LivingObject obj : this.trashList)
		{
			if (obj.equals(parLiving))
			{
				return true;
			}
		}

		return false;
	}

	/**Returns true if instance exists in main & spawn list*/
	public boolean existsLiving(LivingObject parLiving)
	{
		return this.exists(parLiving) || this.existsSpawn(parLiving);
	}

	/**Get main list; safe way to access list*/
	public List<LivingObject> getList()
	{
		return (List<LivingObject>) (this.livingList);//(List<LivingObject>) (this.livingList.clone());
	}

	/**Get spawn list; safe way to access list*/
	public List<LivingObject> getSpawnList()
	{
		return (List<LivingObject>) (this.initList);//(List<LivingObject>) (this.initList.clone());
	}

	/**Get spawn list; safe way to access list*/
	public List<LivingObject> getTrashList()
	{
		return (List<LivingObject>) (this.trashList);//(List<LivingObject>) (this.initList.clone());
	}
}
