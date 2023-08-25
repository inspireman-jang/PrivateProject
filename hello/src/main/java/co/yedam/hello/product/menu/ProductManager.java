package co.yedam.hello.product.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.hello.product.service.ProductService;
import co.yedam.hello.product.service.ProductVO;
import co.yedam.hello.product.serviceImpl.ProductServiceImpl;

public class ProductManager { // 제품관리 메뉴
	private Scanner sc = new Scanner(System.in);
	ProductService dao = new ProductServiceImpl();

	private void mainTitle() {

		System.out.println("========================");
		System.out.println("======제  품  관  리======");
		System.out.println("====1.제 품 전 체 조 회====");
		System.out.println("====2.제 품 한 건 조 회====");
		System.out.println("====3.제 품 등 록=========");
		System.out.println("====4.제 품 수 정=========");
		System.out.println("====5.제 품 삭 제=========");
		System.out.println("====6.종         료======");
		System.out.println("========================");
		System.out.println("= 작업 번호를 선택 하세요.. ");
	}

	public void run() {
		boolean b = false;
		do {
			mainTitle();
			int jobCode = sc.nextInt();
			sc.nextLine();

			switch (jobCode) {
			case 1:
				// 제품 전체 조회
				System.out.println("--------------------------");
				System.out.println("------제 품 전 체 목 록------");
				System.out.println("--------------------------");
				productList(); // 조회 메소드
				System.out.println("--------------------------");
				break;
			case 2:
				// 제품 한건 조회
				System.out.println("--------------------------");
				System.out.print("조회할 제품 코드를 입력하세요>> ");
				String id = sc.nextLine();
				productSelect(id);
				break;
			case 3:
				// 제품 등록
				System.out.println("------제 품 등 록------");
				productInsert();
				break;
			case 4:
				// 제품 수정
				System.out.println("------제 품 수 정------");
				productUpdate();
				break;
			case 5:
				// 제품 삭제
				System.out.println("------제 품 삭 제------");
				productDelete();
				break;
			case 6:
				// 종료
				b = true;
				System.out.println("작업이 종료됩니다.");
				sc.close();
				break;

			default:
				;
			}
		} while (!b);
	}

	private void productDelete() {
		// 제품 삭제
		ProductVO vo = new ProductVO();
		System.out.println("===수정할 제품의 코드를 입력하세요.");
		vo.setProductId(sc.nextLine());
		
		int n = dao.productDelete(vo);
		
		if (n != 0) {
			System.out.println("제품 삭제 완료");
		} else {
			System.out.println("제품 삭제 실패");
		}
		
	}

	private void productUpdate() {
		// 제품 수정
		ProductVO vo = new ProductVO();
		System.out.println("===수정할 제품의 코드를 입력하세요.");
		vo.setProductId(sc.nextLine());
		sutitle();

		int key = Integer.parseInt(sc.nextLine());

		switch (key) {
		case 1: // 전체수정
			System.out.print("제품명을 입력하세요>> ");
			vo.setProductName(sc.nextLine());
			System.out.print("제품가격을 입력하세요>> ");
			vo.setProductPrice(Integer.parseInt(sc.nextLine()));
			System.out.print("제품모델을 입력하세요>> ");
			vo.setProductModel(sc.nextLine());
			break;
		case 2: // 제품명수정
			System.out.print("제품명을 입력하세요>> ");
			vo.setProductName(sc.nextLine());
			break;
		case 3: // 제품가격수정
			System.out.print("제품가격을 입력하세요>> ");
			vo.setProductPrice(Integer.parseInt(sc.nextLine()));
			break;
		case 4: // 제품모델수정
			System.out.print("제품모델을 입력하세요>> ");
			vo.setProductModel(sc.nextLine());
			break;
		default:
			System.out.println("1~4번항목을 선택해주세요.");
		}

		int n = dao.productUpdate(vo);

		if (n != 0) {
			System.out.println("제품 정보 변경이 완료");
		} else {
			System.out.println("제품 정보 변경을 실패");
		}

	}

	private void sutitle() {
		System.out.println("===수정할 항목을 입력하세요.");
		System.out.println("===1. 전체수정");
		System.out.println("===2. 제품명");
		System.out.println("===3. 제품가격");
		System.out.println("===4. 제품모델");
		System.out.print("선택>> ");
	}

	private void productInsert() {
		// 제품등록
		ProductVO vo = new ProductVO();

		System.out.print("제품코드를 입력하세요>>");
		vo.setProductId(sc.nextLine());
		System.out.print("제품명을 입력하세요>>");
		vo.setProductName(sc.nextLine());
		System.out.print("제품가격을 입력하세요>>");
		vo.setProductPrice(Integer.parseInt(sc.nextLine()));
		System.out.print("제품규격을 입력하세요>>");
		vo.setProductModel(sc.nextLine());

		int n = dao.productInsert(vo);

		if (n != 0) {
			System.out.println("==제품 등록이 완료되었습니다.==");
		} else {
			System.out.println("==제품 등록을 실패하였습니다.==");
		}
	}

	private void productSelect(String id) {
		// 제품 한건 조회
		ProductVO vo = new ProductVO();
		vo.setProductId(id);
		vo = dao.productSelect(vo);

		if (vo != null) {
			vo.toString();
		} else {
			System.out.println("제품코드가 존재하지 않습니다.");
		}

	}

	private void productList() {
		// 제품 전체 목록 조회
		List<ProductVO> products = new ArrayList<ProductVO>();

		products = dao.productSelectList();
		for (ProductVO p : products) {
			p.toString();
		}

	}
}
