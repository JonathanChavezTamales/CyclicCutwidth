for entry in $1*
do
	for i in {1..5}
	do
		file=$(echo $entry | rev | cut -d '/' -f-1 | rev)
		java Main < $entry/$file.mtx.rnd2 > $entry/$file.$i.result

	done
	echo "$entry processed"
done
