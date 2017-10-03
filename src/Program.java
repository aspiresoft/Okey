import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Program
{

	public static void main(String[] args)
	{
		// 52 * 2 = 104 + 2 Sahte Okey
		int TAS_SAYISI = 106;

		// Dizi tanimla
		Integer taslar[] = new Integer[TAS_SAYISI];

		// Taslari Bu diziye doldur
		for (int i = 0; i < TAS_SAYISI; i++)
		{
			taslar[i] = i;
		}
		/*
		 * Taslari rastgele karmak icin Java.Util Kutuphanesinin
		 * Collection.shuffle fonksiyonunu kullanacagiz
		 */

		// Array'i List' cevir
		List<Integer> list = Arrays.asList(taslar);
		// List'i rastgele diz
		Collections.shuffle(list);
		// List'i tekrar Array'a cevir
		list.toArray(taslar);

		/*
		 * Okey ve gosterge tas belirleyelim.Ýlk 52 tas arasindan gosterge
		 * belirledik. Sahte okey'in gosterge olma durumunu yok saydim. Gelse de
		 * tekrar okey belirlenecegi icin. Listeyi de ikiye boldum. ayni taslar
		 * zaten, olasilik ayni kalacak.
		 */
		int gosterge = new Random().nextInt((TAS_SAYISI / 2) - 1);

		/*
		 * Sira okeyde. Okey gostergenin 1 fazlasidir. Ancak 13'lu gelme
		 * durumunda okey 1'li olarak belirlenir
		 */
		int okey1, okey2;
		if (gosterge == 12 || gosterge == 25 || gosterge == 38 || gosterge == 51)
		{
			okey1 = gosterge + 1 - 13; // 13 gelmis, 1'li yi okey yap
		}
		else
		{
			okey1 = gosterge + 1; // 13 gelmemis, 1 fazlasini okey yap
		}
		// listeyi ikiye bolmustuk. Ýkinci okeyi de birinci okeyle ayni
		// yapiyoruz.
		okey2 = okey1 + 53;

		/*
		 * Oyuncularin ellerini bir matriste tutacagiz. 4 oyuncu ve her
		 * oyuncunun elinde 14, Tas atacak oyuncunun elinde 15 tas olucak. 14
		 * tas olan oyuncularin 15. taslarini (yani olmayan) -1 ile
		 * gosterecegiz. Bu yuzden bu matrisi -1 ile dolduruyoruz.
		 */
		ArrayList<Tas> oyuncu1El = new ArrayList<Tas>();
		ArrayList<Tas> oyuncu2El = new ArrayList<Tas>();
		ArrayList<Tas> oyuncu3El = new ArrayList<Tas>();
		ArrayList<Tas> oyuncu4El = new ArrayList<Tas>();

		for (int i = 0; i < 14; i++)
		{
			Tas t = new Tas(taslar[i]);
			if (t.isSahteMi())
			{
				t.ayarla(okey1);
			}
			oyuncu1El.add(t);
		}
		for (int i = 14; i < 28; i++)
		{
			Tas t = new Tas(taslar[i]);
			if (t.isSahteMi())
			{
				t.ayarla(okey1);
			}
			oyuncu2El.add(t);
		}
		for (int i = 28; i < 42; i++)
		{
			Tas t = new Tas(taslar[i]);
			if (t.isSahteMi())
			{
				t.ayarla(okey1);
			}
			oyuncu3El.add(t);
		}
		for (int i = 42; i < 56; i++)
		{
			Tas t = new Tas(taslar[i]);
			if (t.isSahteMi())
			{
				t.ayarla(okey1);
			}
			oyuncu4El.add(t);
		}

		// Rastgele birisine 15. tasi verdik
		int r = new Random().nextInt(4);
		if (r == 0)
		{
			oyuncu1El.add(new Tas(taslar[56]));
		}
		else if (r == 1)
		{
			oyuncu2El.add(new Tas(taslar[56]));
		}
		else if (r == 2)
		{
			oyuncu3El.add(new Tas(taslar[56]));
		}
		else
		{
			oyuncu4El.add(new Tas(taslar[56]));
		}

		Collections.sort(oyuncu1El);
		Collections.sort(oyuncu2El);
		Collections.sort(oyuncu3El);
		Collections.sort(oyuncu4El);

		ArrayList<Tas> oyuncu1ElPer = new ArrayList<Tas>();
		ArrayList<Tas> oyuncu2ElPer = new ArrayList<Tas>();
		ArrayList<Tas> oyuncu3ElPer = new ArrayList<Tas>();
		ArrayList<Tas> oyuncu4ElPer = new ArrayList<Tas>();

		System.out.println("===========");
		System.out.println("OYUNCU 1 EL");
		System.out.println("===========");
		for (Tas tas : oyuncu1El)
		{
			System.out.println(tas);

			// ardisik

			ArrayList<Tas> olusanPer = new ArrayList<Tas>();

			if (!oyuncu1ElPer.contains(tas))
			{
				olusanPer.add(tas);
				ardisikEkle(tas, oyuncu1El, oyuncu1ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu1ElPer.add(t);
					}

				}
			}
			olusanPer.clear();

			// normal

			if (!oyuncu1ElPer.contains(tas))
			{
				olusanPer.add(tas);
				normalEkle(tas, oyuncu1El, oyuncu1ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu1ElPer.add(t);
					}

				}
			}
			olusanPer.clear();
		}
		System.out.println("===============");
		System.out.println("OYUNCU 1 PERLER");
		System.out.println("===============");
		for (Tas tas : oyuncu1ElPer)
		{
			System.out.println(tas);
		}
		System.out.println("===========");
		System.out.println("OYUNCU 2 EL");
		System.out.println("===========");
		for (Tas tas : oyuncu2El)
		{
			System.out.println(tas);

			// ardisik

			ArrayList<Tas> olusanPer = new ArrayList<Tas>();

			if (!oyuncu2ElPer.contains(tas))
			{
				olusanPer.add(tas);
				ardisikEkle(tas, oyuncu2El, oyuncu2ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu2ElPer.add(t);
					}

				}
			}
			olusanPer.clear();

			// normal

			if (!oyuncu2ElPer.contains(tas))
			{
				olusanPer.add(tas);
				normalEkle(tas, oyuncu2El, oyuncu2ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu2ElPer.add(t);
					}

				}
			}
			olusanPer.clear();
		}
		System.out.println("===============");
		System.out.println("OYUNCU 2 PERLER");
		System.out.println("===============");
		for (Tas tas : oyuncu2ElPer)
		{
			System.out.println(tas);
		}
		System.out.println("===========");
		System.out.println("OYUNCU 3 EL");
		System.out.println("===========");
		for (Tas tas : oyuncu3El)
		{
			System.out.println(tas);

			// ardisik

			ArrayList<Tas> olusanPer = new ArrayList<Tas>();

			if (!oyuncu3ElPer.contains(tas))
			{
				olusanPer.add(tas);
				ardisikEkle(tas, oyuncu3El, oyuncu3ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu3ElPer.add(t);
					}

				}
			}
			olusanPer.clear();

			// normal

			if (!oyuncu3ElPer.contains(tas))
			{
				olusanPer.add(tas);
				normalEkle(tas, oyuncu3El, oyuncu3ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu3ElPer.add(t);
					}

				}
			}
			olusanPer.clear();
		}
		System.out.println("===============");
		System.out.println("OYUNCU 3 PERLER");
		System.out.println("===============");
		for (Tas tas : oyuncu3ElPer)
		{
			System.out.println(tas);
		}
		System.out.println("===========");
		System.out.println("OYUNCU 4 EL");
		System.out.println("===========");
		for (Tas tas : oyuncu4El)
		{
			System.out.println(tas);

			// ardisik

			ArrayList<Tas> olusanPer = new ArrayList<Tas>();

			if (!oyuncu4ElPer.contains(tas))
			{
				olusanPer.add(tas);
				ardisikEkle(tas, oyuncu4El, oyuncu4ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu4ElPer.add(t);
					}

				}
			}
			olusanPer.clear();

			// normal

			if (!oyuncu4ElPer.contains(tas))
			{
				olusanPer.add(tas);
				normalEkle(tas, oyuncu4El, oyuncu4ElPer, olusanPer);

				if (olusanPer.size() >= 3)
				{
					for (Tas t : olusanPer)
					{
						oyuncu4ElPer.add(t);
					}

				}
			}
			olusanPer.clear();
		}
		System.out.println("===============");
		System.out.println("OYUNCU 4 PERLER");
		System.out.println("===============");
		for (Tas tas : oyuncu4ElPer)
		{
			System.out.println(tas);
		}
		System.out.println("=============================================");
		System.out.println("Perlerde en cok tasi olan en iyi eli olandir.");
		System.out.println("=============================================");
		
		
		System.out.println("Oyuncu1 'in perdeki taslarinin sayisi = " + oyuncu1ElPer.size());
		System.out.println("Oyuncu2 'in perdeki taslarinin sayisi = " + oyuncu2ElPer.size());
		System.out.println("Oyuncu3 'in perdeki taslarinin sayisi = " + oyuncu3ElPer.size());
		System.out.println("Oyuncu4 'in perdeki taslarinin sayisi = " + oyuncu4ElPer.size());
		
	}

	public static void ardisikEkle(Tas tas, ArrayList<Tas> oyuncuEli, ArrayList<Tas> oyuncuEliPer, ArrayList<Tas> olusanPer)
	{
		if (!oyuncuEliPer.contains(tas))
		{
			for (Tas t : oyuncuEli)
			{
				if (!t.equals(tas) && t.getRenk() == tas.getRenk() && t.getSayi() == (tas.getSayi() + 1) && !olusanPer.contains(t)
								&& !oyuncuEliPer.contains(t))
				{
					boolean hasSame = false;
					Iterator<Tas> perIterator = olusanPer.iterator();
					while (perIterator.hasNext())
					{
						Tas t2 = perIterator.next();
						if (t2.getRenk() == t.getRenk() && t2.getSayi() == t.getSayi())
						{
							hasSame = true;
						}
					}
					if (!hasSame)
					{
						olusanPer.add(t);
						ardisikEkle(t, oyuncuEli, oyuncuEliPer, olusanPer);
					}
				}
			}
		}
	}

	public static void normalEkle(Tas tas, ArrayList<Tas> oyuncuEli, ArrayList<Tas> oyuncuEliPer, ArrayList<Tas> olusanPer)
	{
		if (!oyuncuEliPer.contains(tas))
		{
			for (Tas t : oyuncuEli)
			{
				if (!t.equals(tas) && t.getRenk() != (tas.getRenk()) && t.getSayi() == (tas.getSayi()) && !olusanPer.contains(t)
								&& !oyuncuEliPer.contains(t))
				{
					boolean hasSame = false;
					Iterator<Tas> perIterator = olusanPer.iterator();
					while (perIterator.hasNext())
					{
						Tas t2 = perIterator.next();
						if (t2.getRenk() == t.getRenk() && t2.getSayi() == t.getSayi())
						{
							hasSame = true;
						}
					}
					if (!hasSame)
					{
						olusanPer.add(t);
						normalEkle(t, oyuncuEli, oyuncuEliPer, olusanPer);
					}
				}
			}
		}
	}

}
