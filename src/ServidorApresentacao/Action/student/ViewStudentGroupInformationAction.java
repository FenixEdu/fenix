/*
 * Created on 26/Ago/2003
 *
 */
package ServidorApresentacao.Action.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import DataBeans.ISiteComponent;
import DataBeans.InfoSiteStudentGroup;
import ServidorAplicacao.IUserView;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorApresentacao.Action.base.FenixContextAction;
import ServidorApresentacao.Action.exceptions.FenixActionException;
import ServidorApresentacao.Action.sop.utils.ServiceUtils;
import ServidorApresentacao.Action.sop.utils.SessionConstants;

/**
 * @author asnr and scpo
 *
 */
public class ViewStudentGroupInformationAction extends FenixContextAction {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws FenixActionException {

		HttpSession session = request.getSession(false);
		IUserView userView =
			(IUserView) session.getAttribute(SessionConstants.U_VIEW);
		System.out.println("ENTRA NA ACCAO PARA VER A INF DE CADA PROJECTO");

		String studentGroupCodeString =
			request.getParameter("studentGroupCode");

		Integer studentGroupCode = new Integer(studentGroupCodeString);

		ISiteComponent viewStudentGroup;
		Object[] args = { studentGroupCode };
		try {
			viewStudentGroup =
				(InfoSiteStudentGroup) ServiceUtils.executeService(
					userView,
					"ReadStudentGroupInformation",
					args);

		} catch (FenixServiceException e) {
			throw new FenixActionException(e);
		}
		System.out.println("Dp do servico");

		List infoSiteStudentInformationList =
			((InfoSiteStudentGroup) viewStudentGroup)
				.getInfoSiteStudentInformationList();

		request.setAttribute(
			"infoSiteStudentInformationList",
			infoSiteStudentInformationList);

		return mapping.findForward("sucess");
	}
}
