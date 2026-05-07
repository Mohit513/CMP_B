package com.example.cmp_b.shared.data.mapper

import com.example.cmp_b.core.base.Mapper
import com.example.cmp_b.core.network.model.auth.CandidateLoginResponseDto
import com.example.cmp_b.core.network.model.auth.LoginOtpValidateResponseDto
import com.example.cmp_b.shared.domain.model.auth.CandidateLoginUiModel
import com.example.cmp_b.shared.domain.model.auth.LoginOtpValidateUiModel

class CandidateLoginMapper : Mapper<CandidateLoginResponseDto, CandidateLoginUiModel> {
    override fun mapFrom(from: CandidateLoginResponseDto): CandidateLoginUiModel {
        return CandidateLoginUiModel(
            candidateStatus = from.candidateStatus,
            innovID = from.innovID,
            isDummy = from.isDummy,
            isMigrated = from.isMigrated,
            isTransferred = from.isTransferred,
            mobile = from.mobile,
            otp = from.otp,
            status = from.status
        )
    }
}

class LoginOtpValidateMapper : Mapper<LoginOtpValidateResponseDto, LoginOtpValidateUiModel> {
    override fun mapFrom(from: LoginOtpValidateResponseDto): LoginOtpValidateUiModel {
        return LoginOtpValidateUiModel(
            innovID = from.innovID,
            otpStatus = from.otpStatus,
            tokenID = from.tokenID
        )
    }
}
