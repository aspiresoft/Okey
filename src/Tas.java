
public class Tas implements Comparable<Tas>
{

	private int		sayi;
	private String	renk;

	private boolean	sahteMi;

	public Tas(int id)
	{
		sahteMi = false;
		if (id == 52 || id == 105)
		{
			sahteMi = true;
		}
		ayarla(id);
	}

	public void ayarla(int id)
	{
		int tempId = id % 53;
		setSayi(tempId % 13 + 1);
		if (tempId < 13)
		{
			setRenk("SARI");
		}
		else if (tempId < 26)
		{
			setRenk("MAVI");
		}
		else if (tempId < 39)
		{
			setRenk("SIYAH");
		}
		else if (tempId < 52)
		{
			setRenk("KIRMIZI");
		}
	}

	public int getSayi()
	{
		return sayi;
	}

	public void setSayi(int sayi)
	{
		this.sayi = sayi;
	}

	public String getRenk()
	{
		return renk;
	}

	public void setRenk(String renk)
	{
		this.renk = renk;
	}

	@Override
	public String toString()
	{
		return this.renk + " " + this.sayi + (isSahteMi() ? " + SAHTE" : "");
	}

	@Override
	public int compareTo(Tas o)
	{
		return this.sayi - o.sayi;
	}

	public boolean isSahteMi()
	{
		return sahteMi;
	}

	public void setSahteMi(boolean sahteMi)
	{
		this.sahteMi = sahteMi;
	}

}
