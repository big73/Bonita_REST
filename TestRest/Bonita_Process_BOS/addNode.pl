use strict;
use warnings;
use XML::Twig;


my $reference_file;
my $output_file ;

#	Contrôle des paramètres
my $nbparam = $#ARGV;
if ($nbparam == 1)
{
	$reference_file = shift;
	$output_file = shift;
}
else
{
	die "Nombre incorrect de paramètres \n 
	
	Utiliser la syntaxe suivante : \n 
	perl $0 <fichier_reference.xml> <fichier_de_sortie.xml> \n";
}
																						
#	initialisation des twigger
my $twig_reference = new XML::Twig;
my $twig_output = new XML::Twig;

addNodeOperation();

sub addNodeOperation
{
	#	préparation de l'environnement des variables en mémoire
		#	initialisation du twig sur le fichier de référence
		$twig_reference -> parsefile($reference_file);
		my $twig_reference_root = $twig_reference -> root ;
		
		my $twig_output_root = $twig_reference_root;
		$twig_output_root -> insert_new_elt('enrichissement');
		$twig_output_root -> print_to_file($output_file);
}