/*
 * Created on 26/Ago/2003
 *

 */
package ServidorAplicacao.Servico.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

import DataBeans.ISiteComponent;
import DataBeans.InfoSiteStudentGroup;
import DataBeans.InfoSiteStudentInformation;
import DataBeans.InfoStudentGroupAttend;
import DataBeans.util.Cloner;
import Dominio.IStudentGroup;
import Dominio.IStudentGroupAttend;
import Dominio.StudentGroup;
import ServidorAplicacao.IServico;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;

/**
 * @author asnr and scpo
 *
 */
public class ReadStudentGroupInformation implements IServico {

	private static ReadStudentGroupInformation _servico =
		new ReadStudentGroupInformation();
	/**
	 * The singleton access method of this class.
	 **/
	public static ReadStudentGroupInformation getService() {
		return _servico;
	}

	/**
	 * The actor of this class.
	 **/
	private ReadStudentGroupInformation() {
	}

	/**
	 * Devolve o nome do servico
	 **/
	public final String getNome() {
		return "ReadStudentGroupInformation";
	}

	public ISiteComponent run(Integer studentGroupCode)
		throws FenixServiceException {

			List studentGroupAttendInformationList = null;
			InfoSiteStudentGroup infoSiteStudentGroup = new InfoSiteStudentGroup();
			try {
				ISuportePersistente sp = SuportePersistenteOJB.getInstance();

				IStudentGroup studentGroup = (IStudentGroup) sp.getIPersistentStudentGroup().readByOId(new StudentGroup(studentGroupCode), false);

				List studentGroupAttendList = sp.getIPersistentStudentGroupAttend().readAllByStudentGroup(studentGroup);

				studentGroupAttendInformationList = new ArrayList(studentGroupAttendList.size());
				Iterator iter = studentGroupAttendList.iterator();
				InfoSiteStudentInformation infoSiteStudentInformation = null;
				InfoStudentGroupAttend infoStudentGroupAttend = null;

				while (iter.hasNext()) {

					infoSiteStudentInformation = new InfoSiteStudentInformation();

					infoStudentGroupAttend = Cloner.copyIStudentGroupAttend2InfoStudentGroupAttend((IStudentGroupAttend) iter.next());

					infoSiteStudentInformation.setNumber(infoStudentGroupAttend.getInfoAttend().getAluno().getNumber());

					infoSiteStudentInformation.setName(infoStudentGroupAttend.getInfoAttend().getAluno().getInfoPerson().getNome());

					infoSiteStudentInformation.setEmail(infoStudentGroupAttend.getInfoAttend().getAluno().getInfoPerson().getEmail());
				
					infoSiteStudentInformation.setUsername(infoStudentGroupAttend.getInfoAttend().getAluno().getInfoPerson().getUsername());
				
					studentGroupAttendInformationList.add(infoSiteStudentInformation);

				}

				Collections.sort(studentGroupAttendInformationList, new BeanComparator("number"));
				
				infoSiteStudentGroup.setInfoSiteStudentInformationList(studentGroupAttendInformationList);
				} catch (ExcepcaoPersistencia ex) {
				ex.printStackTrace();
			}
			return infoSiteStudentGroup;
	}
}