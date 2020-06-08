for entry in *
do
  mkdir $entry | cut -d '.' -f1
  mv $entry $entry | cut -d '.' -f1
done
